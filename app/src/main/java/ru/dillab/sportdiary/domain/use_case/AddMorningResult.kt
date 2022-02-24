package ru.dillab.sportdiary.domain.use_case

import ru.dillab.sportdiary.domain.model.MorningResult
import ru.dillab.sportdiary.domain.repository.DayResultRepository

class AddMorningResult(private val repository: DayResultRepository) {

    suspend operator fun invoke(morningResult: MorningResult) {
        repository.insertMorningResult(morningResult)
    }
}