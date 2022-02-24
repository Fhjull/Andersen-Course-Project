package ru.dillab.sportdiary.data.remote.dto

import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity
import ru.dillab.sportdiary.formatDateStringToLong
import ru.dillab.sportdiary.generateIdFromString


data class DayResultsDto(
    val majorDimension: String,
    val range: String,
    val values: List<List<String>>
) {
    fun convertToResultsList(): MorningAndEveningResults {
        val morningResults = mutableListOf<MorningResultEntity>()
        val eveningResults = mutableListOf<EveningResultEntity>()

        val listOfRows = values.removeFirstRow()
        listOfRows.forEach { originalRow ->
            // Adding empty strings in order not to invoke ArrayIndexOutOfBoundsException
            val row = originalRow.addEmptyStrings()

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


    private fun List<String>.addEmptyStrings(): List<String> {
        val row = this.toMutableList()
        repeat(MAX_COLUMNS_IN_ROW) {
            row.add("")
        }
        return row
    }

    companion object {
        private const val MORNING_RESULT_IDENTIFIER = "Утренний опросник"
        private const val EVENING_RESULT_IDENTIFIER = "Вечерний опросник"
        private const val MAX_COLUMNS_IN_ROW = 4
    }
}