package ru.dillab.sportdiary.data.remote.dto

import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity

data class MorningAndEveningResults(
    val morningResults: List<MorningResultEntity>,
    val eveningResults: List<EveningResultEntity>
)