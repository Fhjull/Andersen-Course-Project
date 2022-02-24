package ru.dillab.sportdiary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dillab.sportdiary.domain.model.MorningResult

@Entity(tableName = "morning_results")
data class MorningResultEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "morning_time") val morningTime: Long,
    @ColumnInfo(name = "sleep_hours") val sleepHours: String? = null,
    @ColumnInfo(name = "pulse") val pulse: String? = null,
    @ColumnInfo(name = "muscle_pain") val musclePain: String? = null,
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