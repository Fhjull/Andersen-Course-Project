package ru.dillab.sportdiary.domain.model

import ru.dillab.sportdiary.data.local.entity.DayResultEntity

data class DayResult(
    val id: Int,
    val morningTime: Long? = null,
    val eveningTime: Long? = null,
    val sleepHours: String? = null,
    val pulse: String? = null,
    val musclePain: String? = null,
    val productivity: String? = null,
    val goals: String? = null,
    val qualities: String? = null
) {
    fun toDayResultEntity(): DayResultEntity {
        return DayResultEntity(
            id = id,
            morningTime = morningTime,
            eveningTime = eveningTime,
            sleepHours = sleepHours,
            pulse = pulse,
            musclePain = musclePain,
            productivity = productivity,
            goals = goals,
            qualities = qualities
        )
    }
}