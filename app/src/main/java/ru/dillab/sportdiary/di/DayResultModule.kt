package ru.dillab.sportdiary.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dillab.sportdiary.data.local.DayResultsRoomDatabase
import ru.dillab.sportdiary.data.remote.DayResultsApi
import ru.dillab.sportdiary.data.repository.DayResultRepositoryImpl
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.domain.use_case.GetDayResults
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DayResultModule {

    @Provides
    @Singleton
    fun provideGetDayResultsUseCase(repository: DayResultRepository): GetDayResults {
        return GetDayResults(repository)
    }

    @Provides
    @Singleton
    fun provideDayResultRepository(
        api: DayResultsApi,
        db: DayResultsRoomDatabase
    ): DayResultRepository {
        return DayResultRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideDayResultsRoomDatabase(app: Application): DayResultsRoomDatabase {
        return Room.databaseBuilder(
            app, DayResultsRoomDatabase::class.java, "results_database"
        ).build()
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