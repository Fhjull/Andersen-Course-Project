package ru.dillab.sportdiary.data.local.entity

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dillab.sportdiary.domain.model.DayResult

@Entity(tableName = "day_results")
data class DayResultEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "morning_time") val morningTime: Long? = null,
    @ColumnInfo(name = "evening_time") val eveningTime: Long? = null,
    @ColumnInfo(name = "sleep_hours") val sleepHours: String? = null,
    @ColumnInfo(name = "pulse") val pulse: String? = null,
    @ColumnInfo(name = "muscle_pain") val musclePain: String? = null,
    @ColumnInfo(name = "productivity") val productivity: String? = null,
    @ColumnInfo(name = "goals") val goals: String? = null,
    @ColumnInfo(name = "qualities") val qualities: String? = null
) {
    fun toDayResult(): DayResult {
        return DayResult(
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