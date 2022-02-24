package ru.dillab.sportdiary.domain.use_case

data class DayResultUseCases(
    val getDayResults: GetDayResults,
    val getTodaysMorningResult: GetTodaysMorningResult,
    val getTodaysEveningResult: GetTodaysEveningResult,
    val addMorningResult: AddMorningResult,
    val addEveningResult: AddEveningResult
)