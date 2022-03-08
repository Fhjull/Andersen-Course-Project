package ru.dillab.sportdiary.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.domain.model.MorningResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository
import ru.dillab.sportdiary.utils.generateIdFromLong
import java.util.*

class GetTodaysMorningResult(private val repository: DayResultRepository) {

    operator fun invoke(): Flow<MorningResult?> {
        val currentTime = Calendar.getInstance().timeInMillis
        val id = currentTime.generateIdFromLong()
        return repository.getMorningResultById(id)
    }
}