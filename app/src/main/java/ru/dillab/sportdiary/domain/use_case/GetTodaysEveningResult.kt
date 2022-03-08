package ru.dillab.sportdiary.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.domain.model.EveningResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.utils.generateIdFromLong
import java.util.*

class GetTodaysEveningResult(private val repository: DayResultRepository) {

    operator fun invoke(): Flow<EveningResult?> {
        val currentTime = Calendar.getInstance().timeInMillis
        val id = currentTime.generateIdFromLong()
        return repository.getEveningResultById(id)
    }
}