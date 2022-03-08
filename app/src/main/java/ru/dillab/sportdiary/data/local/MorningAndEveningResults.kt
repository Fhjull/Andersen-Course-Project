package ru.dillab.sportdiary.data.local

import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity
import ru.dillab.sportdiary.domain.model.DayResult

// This class serves as helper to get data from ROOM database. If complex SQL query "getDayResults()
// will be fixed, this class can be deleted
data class MorningAndEveningResults(
    val morningResults: List<MorningResultEntity>,
    val eveningResults: List<EveningResultEntity>
) {
    fun toDayResults(): List<DayResult> {
        val listOfDayResults = mutableListOf<DayResult>()
        val setOfIds = mutableSetOf<Int>()
        morningResults.forEach { setOfIds.add(it.id) }
        eveningResults.forEach { setOfIds.add(it.id) }
        setOfIds.forEach { id ->
            var morningData: MorningResultEntity? = null
            var eveningData: EveningResultEntity? = null
            morningResults.forEach { if (it.id == id) morningData = it }
            eveningResults.forEach { if (it.id == id) eveningData = it }
            listOfDayResults.add(
                DayResult(
                    id = id,
                    morningTime = morningData?.morningTime,
                    sleepHours = morningData?.sleepHours,
                    pulse = morningData?.pulse,
                    musclePain = morningData?.musclePain,
                    eveningTime = eveningData?.eveningTime,
                    productivity = eveningData?.productivity,
                    goals = eveningData?.goals,
                    qualities = eveningData?.qualities
                )
            )
        }
        return listOfDayResults.sortedByDescending { it.id }
    }
}