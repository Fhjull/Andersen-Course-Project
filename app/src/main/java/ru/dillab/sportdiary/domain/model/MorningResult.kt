package ru.dillab.sportdiary.domain.model

import ru.dillab.sportdiary.data.local.entity.MorningResultEntity


data class MorningResult(
    val id: Int,
    val morningTime: Long,
    val sleepHours: String? = null,
    val pulse: String? = null,
    val musclePain: String? = null,
) {
    fun toMorningResultEntity(): MorningResultEntity {
        return MorningResultEntity(
            id = id,
            morningTime = morningTime,
            sleepHours = sleepHours,
            pulse = pulse,
            musclePain = musclePain,
        )
    }
}