package ru.dillab.sportdiary.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.utils.Resource

interface DayResultRepository {

    fun getDayResults(): Flow<Resource<List<DayResult>>>
}