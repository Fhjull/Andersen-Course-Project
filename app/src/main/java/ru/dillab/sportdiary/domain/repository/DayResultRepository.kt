package ru.dillab.sportdiary.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.model.EveningResult
import ru.dillab.sportdiary.domain.model.MorningResult
import ru.dillab.sportdiary.utils.ServerState

interface DayResultRepository {

    fun getDayResults(): Flow<ServerState<List<DayResult>>>

    fun getMorningResultById(id: Int): Flow<MorningResult?>

    fun getEveningResultById(id: Int): Flow<EveningResult?>

    suspend fun insertMorningResult(morningResult: MorningResult)

    suspend fun insertEveningResult(eveningResult: EveningResult)
}