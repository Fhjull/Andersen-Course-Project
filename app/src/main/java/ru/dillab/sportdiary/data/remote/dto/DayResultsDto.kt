package ru.dillab.sportdiary.data.remote.dto

import ru.dillab.sportdiary.data.local.MorningAndEveningResults
import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity
import ru.dillab.sportdiary.utils.formatDateStringToLong
import ru.dillab.sportdiary.utils.generateIdFromString

data class DayResultsDto(
    val majorDimension: String,
    val range: String,
    val values: List<List<String>>
) {
    /*
    Example from a google sheet, how it looks now
    21.02.2022 13:33:17 / Утренний опросник / 6 - 8 часов / 76 / 2 /    /                     /              /
    21.02.2022 21:41:38 / Вечерний опросник /             /    /   / Да / Тренировка на улице /	Выносливость /
    */
    fun convertToResultsList(): MorningAndEveningResults {
        val morningResults = mutableListOf<MorningResultEntity>()
        val eveningResults = mutableListOf<EveningResultEntity>()

        // Removing first row with columns header texts
        val resultsTable = values.removeFirstRow()

        // for each row we construct either morning or evening ResultEntity
        resultsTable.forEach {
            // Adding empty columns in order not to invoke ArrayIndexOutOfBoundsException
            val row = it.addEmptyColumns()

            val id = row[0].generateIdFromString()
            val time = row[0].formatDateStringToLong()
            when (row[1]) {
                MORNING_RESULT_IDENTIFIER -> {
                    val morningResult = MorningResultEntity(
                        id = id,
                        morningTime = time,
                        sleepHours = row[2].ifEmpty { null },
                        pulse = row[3].ifEmpty { null },
                        musclePain = row[4].ifEmpty { null }
                    )
                    morningResults.add(morningResult)
                }
                EVENING_RESULT_IDENTIFIER -> {
                    val eveningResult = EveningResultEntity(
                        id = id,
                        eveningTime = time,
                        productivity = row[5].ifEmpty { null },
                        goals = row[6].ifEmpty { null },
                        qualities = row[7].ifEmpty { null }
                    )
                    eveningResults.add(eveningResult)
                }
            }
        }

        return MorningAndEveningResults(morningResults, eveningResults)
    }

    private fun List<List<String>>.removeFirstRow(): List<List<String>> {
        val list = this.toMutableList()
        list.removeAt(0)
        return list
    }


    private fun List<String>.addEmptyColumns(): List<String> {
        val row = this.toMutableList()
        repeat(MAX_COLUMNS_IN_ROW) {
            row.add("")
        }
        return row
    }

    companion object {
        private const val MORNING_RESULT_IDENTIFIER = "Утренний опросник"
        private const val EVENING_RESULT_IDENTIFIER = "Вечерний опросник"

        // This one must be number of max possible empty columns in our google sheet (columns after
        // "Утренний опросник" / "Вечерний опросник" entry)
        private const val MAX_COLUMNS_IN_ROW = 6
    }
}