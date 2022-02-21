package ru.dillab.sportdiary.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.utils.Resource

class GetDayResults(
    private val repository: DayResultRepository
) {

    operator fun invoke(): Flow<Resource<List<DayResult>>> {
        return repository.getDayResults()
    }
}