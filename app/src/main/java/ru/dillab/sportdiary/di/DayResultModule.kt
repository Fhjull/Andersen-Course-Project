package ru.dillab.sportdiary.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dillab.sportdiary.data.local.DayResultsDatabase
import ru.dillab.sportdiary.data.remote.DayResultsApi
import ru.dillab.sportdiary.data.repository.DayResultRepositoryImpl
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DayResultModule {

    @Provides
    @Singleton
    fun provideDayResultUseCases(repository: DayResultRepository): DayResultUseCases {
        return DayResultUseCases(
            getDayResults = GetDayResults(repository),
            getTodaysMorningResult = GetTodaysMorningResult(repository),
            getTodaysEveningResult = GetTodaysEveningResult(repository),
            addMorningResult = AddMorningResult(repository),
            addEveningResult = AddEveningResult(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDayResultsRepository(
        api: DayResultsApi,
        db: DayResultsDatabase
    ): DayResultRepository {
        return DayResultRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideDayResultsDatabase(app: Application): DayResultsDatabase {
        return Room.databaseBuilder(
            app, DayResultsDatabase::class.java, "results_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDayResultsApi(): DayResultsApi {
        return Retrofit.Builder()
            .baseUrl(DayResultsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DayResultsApi::class.java)
    }
}