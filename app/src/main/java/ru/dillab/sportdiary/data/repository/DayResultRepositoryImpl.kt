package ru.dillab.sportdiary.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import ru.dillab.sportdiary.data.local.DayResultDao
import ru.dillab.sportdiary.data.remote.DayResultsApi
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.model.EveningResult
import ru.dillab.sportdiary.domain.model.MorningResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.utils.ServerState
import java.io.IOException

class DayResultRepositoryImpl(
    private val api: DayResultsApi,
    private val dao: DayResultDao
) : DayResultRepository {

    override fun getDayResults(): Flow<ServerState<List<DayResult>>> = flow {
        // Start loading - display progress bar
        emit(ServerState.Loading())

        val dayResults = dao.getDayResults()
        emit(ServerState.Loading(data = dayResults))
        Log.d("testing", "ServerState.Loading(data = $dayResults)")

        // Get data from API
        try {
            val remoteDayResults = api.getDataFromGoogleSheet()

            // if request was successful we can update our ROOM database, if not we continue with catch blocks
            /*
            TODO Implement conflict strategy according to time - dayResults vs remoteDayResults
            or maybe do it Dao @Insert(onConflict) if it is possible
            */

            dao.insertAll(
                remoteDayResults.convertToResultsList().morningResults,
                remoteDayResults.convertToResultsList().eveningResults
            )

            // catch invalid response
        } catch (e: HttpException) {
            Log.d("testing", "DayResultRepositoryImpl HttpException")
            emit(ServerState.Error(message = e.message ?: "HttpException"))

            // catch when parsing went wrong or server not reachable or we don't have internet connection
        } catch (e: IOException) {
            Log.d("testing", "DayResultRepositoryImpl IOException")
            emit(ServerState.Error(message = e.message ?: "IOException"))
        }

        val newDayResults = dao.getDayResults()
        // Set server status to Success
        emit(ServerState.Success(data = newDayResults))
        Log.d("testing", "ServerState.Success($newDayResults)")
    }

    // override fun getDayResults(): Flow<List<DayResult>> {
    //     return dao.getDayResults()
    // }

    override fun getMorningResultById(id: Int): Flow<MorningResult?> {
        return dao.getMorningResultById(id).map { it?.toMorningResult() }
    }

    override fun getEveningResultById(id: Int): Flow<EveningResult?> {
        return dao.getEveningResultById(id).map { it?.toEveningResult() }
    }

    override suspend fun insertMorningResult(morningResult: MorningResult) {
        return dao.insertMorningResult(morningResult.toMorningResultEntity())
    }

    override suspend fun insertEveningResult(eveningResult: EveningResult) {
        return dao.insertEveningResult(eveningResult.toEveningResultEntity())
    }
}