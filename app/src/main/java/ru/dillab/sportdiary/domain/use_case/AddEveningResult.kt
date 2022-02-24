package ru.dillab.sportdiary.domain.use_case

import ru.dillab.sportdiary.domain.model.EveningResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository

class AddEveningResult(private val repository: DayResultRepository) {

    suspend operator fun invoke(eveningResult: EveningResult) {
        repository.insertEveningResult(eveningResult)
    }
}