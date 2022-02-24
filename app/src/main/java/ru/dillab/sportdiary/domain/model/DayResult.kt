package ru.dillab.sportdiary.domain.model

data class DayResult(
    val id: Int,
    val morningTime: Long? = null,
    val sleepHours: String? = null,
    val pulse: String? = null,
    val musclePain: String? = null,
    val eveningTime: Long? = null,
    val productivity: String? = null,
    val goals: String? = null,
    val qualities: String? = null
)