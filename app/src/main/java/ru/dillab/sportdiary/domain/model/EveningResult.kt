package ru.dillab.sportdiary.domain.model

import ru.dillab.sportdiary.data.local.entity.EveningResultEntity


data class EveningResult(
    val id: Int,
    val eveningTime: Long,
    val productivity: String? = null,
    val goals: String? = null,
    val qualities: String? = null
) {
    fun toEveningResultEntity(): EveningResultEntity {
        return EveningResultEntity(
            id = id,
            eveningTime = eveningTime,
            productivity = productivity,
            goals = goals,
            qualities = qualities
        )
    }
}