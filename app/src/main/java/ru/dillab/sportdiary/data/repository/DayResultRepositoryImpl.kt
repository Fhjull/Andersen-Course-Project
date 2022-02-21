package ru.dillab.sportdiary.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import ru.dillab.sportdiary.data.local.DayResultDao
import ru.dillab.sportdiary.data.remote.DayResultsApi
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.utils.Resource
import java.io.IOException

class DayResultRepositoryImpl(
    private val api: DayResultsApi,
    private val dao: DayResultDao
) : DayResultRepository {

    override fun getDayResults(): Flow<Resource<List<DayResult>>> = flow {
        // Start loading - display progress bar
        emit(Resource.Loading())

        // Get data from ROOM database - notify view model that we have data to display
        val dayResults = dao.getDayResults().map { it.toDayResult() }
        emit(Resource.Loading(data = dayResults))

        // Get data from API
        try {
            val remoteDayResults = api.getDataFromGoogleSheet()

            // if request was successful we can update our ROOM database, if not we continue with catch blocks
            /*
            TODO Implement conflict strategy according to time - dayResults vs remoteDayResults
            or maybe do it Dao @Insert(onConflict) if it is possible
            */
            // val r = remoteDayResults.toDayResultsCollection().map { it.toDayResultEntity() }

            dao.insertAll(remoteDayResults.toDayResultsCollection().map { it.toDayResultEntity() })

            // catch invalid response
        } catch (e: HttpException) {
            Log.d("test", "DayResultRepositoryImpl HttpException")
            emit(Resource.Error(
                message = e.message ?: "HttpException",
                data = dayResults
            ))

            // catch when parsing went wrong or server not reachable or we don't have internet connection
        } catch (e: IOException) {
            Log.d("test", "DayResultRepositoryImpl IOException")
            emit(Resource.Error(
                message = e.message ?: "IOException",
                data = dayResults
            ))
        }

        // Update our view model with new entries
        val newDayResults = dao.getDayResults().map { it.toDayResult() }
        emit(Resource.Success(newDayResults))
    }
}