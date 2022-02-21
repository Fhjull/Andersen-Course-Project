package ru.dillab.sportdiary.ui.results

import ru.dillab.sportdiary.domain.model.DayResult

data class ResultsInfoState(
    val dayResults: List<DayResult> = emptyList(),
    val isLoading: Boolean = false
)