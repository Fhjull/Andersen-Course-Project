package ru.dillab.sportdiary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.dillab.sportdiary.domain.model.EveningResult

@Entity(tableName = "evening_results")
data class EveningResultEntity(
    @PrimaryKey val id: Int,
    val eveningTime: Long,
    val productivity: String? = null,
    val goals: String? = null,
    val qualities: String? = null
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