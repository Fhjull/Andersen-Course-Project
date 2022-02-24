package ru.dillab.sportdiary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dillab.sportdiary.domain.model.MorningResult

@Entity(tableName = "morning_results")
data class MorningResultEntity(
    @PrimaryKey val id: Int,
    val morningTime: Long,
    val sleepHours: String? = null,
    val pulse: String? = null,
    val musclePain: String? = null,
) {
    fun toMorningResult(): MorningResult {
        return MorningResult(
            id = id,
            morningTime = morningTime,
            sleepHours = sleepHours,
            pulse = pulse,
            musclePain = musclePain,
        )
    }
}