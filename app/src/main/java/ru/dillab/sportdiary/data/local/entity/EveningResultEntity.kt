package ru.dillab.sportdiary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dillab.sportdiary.domain.model.EveningResult

@Entity(tableName = "evening_results")
data class EveningResultEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "evening_time") val eveningTime: Long,
    @ColumnInfo(name = "productivity") val productivity: String? = null,
    @ColumnInfo(name = "goals") val goals: String? = null,
    @ColumnInfo(name = "qualities") val qualities: String? = null
) {
    fun toEveningResult(): EveningResult {
        return EveningResult(
            id = id,
            eveningTime = eveningTime,
            productivity = productivity,
            goals = goals,
            qualities = qualities
        )
    }
}