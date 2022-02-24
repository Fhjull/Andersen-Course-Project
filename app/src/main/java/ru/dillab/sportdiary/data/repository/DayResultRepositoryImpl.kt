package ru.dillab.sportdiary.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import ru.dillab.sportdiary.data.local.DayResultDao
import ru.dillab.sportdiary.data.remote.DayResultsApi
import ru.dillab.sportdiary.data.remote.dto.MorningAndEveningResults
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

        val morningResults = dao.getMorningResults()
        val eveningResults = dao.getEveningResults()
        val dayResults = MorningAndEveningResults(morningResults, eveningResults).toDayResults()
        emit(ServerState.Loading(data = dayResults))

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

            // Update data from database
            val newMorningResults = dao.getMorningResults()
            val newEveningResults = dao.getEveningResults()
            val newDayResults =
                MorningAndEveningResults(newMorningResults, newEveningResults).toDayResults()
            // Set server status to Success
            emit(ServerState.Success(data = newDayResults))

            // catch invalid response
        } catch (e: HttpException) {
            emit(
                ServerState.Error(
                    data = dayResults,
                    message = e.message ?: "HttpException"
                )
            )

            // catch when parsing went wrong or server not reachable or we don't have internet connection
        } catch (e: IOException) {
            emit(
                ServerState.Error(
                    data = dayResults,
                    message = e.message ?: "IOException"
                )
            )
        }
    }

    // TODO Implement ServerState here
    override fun getMorningResultById(id: Int): Flow<MorningResult?> {
        return dao.getMorningResultById(id).map { it?.toMorningResult() }
    }

    // TODO Implement ServerState here
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