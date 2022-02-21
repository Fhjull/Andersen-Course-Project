package ru.dillab.sportdiary.data.remote.dto

import android.util.Log
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.formatDateStringToLong
import ru.dillab.sportdiary.generateIdFromString

private const val MORNING_RESULT_IDENTIFIER = "Утренний опросник"
private const val EVENING_RESULT_IDENTIFIER = "Вечерний опросник"
private const val MAX_COLUMNS_IN_ROW = 8

data class DayResultsDto(
    val majorDimension: String,
    val range: String,
    val values: List<List<String>>
) {
    fun toDayResultsCollection(): List<DayResult> {
        val listOfRows = values.removeFirstRow()
        val listOfDayResults = listOfRows.map { originalRow ->
            val row = originalRow.addEmptyStrings()

            val id = row[0].generateIdFromString()
            val time = row[0].formatDateStringToLong()
            var morningTime: Long? = null
            var eveningTime: Long? = null
            var sleepHours: String? = null
            var pulse: String? = null
            var musclePain: String? = null
            var productivity: String? = null
            var goals: String? = null
            var qualities: String? = null
            when (row[1]) {
                MORNING_RESULT_IDENTIFIER -> {
                    morningTime = time
                    sleepHours = row[2].ifEmpty { null }
                    pulse = row[3].ifEmpty { null }
                    musclePain = row[4].ifEmpty { null }
                }
                EVENING_RESULT_IDENTIFIER -> {
                    eveningTime = time
                    productivity = row[5].ifEmpty { null }
                    goals = row[6].ifEmpty { null }
                    qualities = row[7].ifEmpty { null }
                }
            }
            DayResult(
                id,
                morningTime,
                eveningTime,
                sleepHours,
                pulse,
                musclePain,
                productivity,
                goals,
                qualities
            )
        }



        val list = mutableListOf<DayResult>()
        var currentId = 0


        Log.d("test", list.toString())



        return listOfDayResults
    }

    private fun List<List<String>>.removeFirstRow(): List<List<String>> {
        val list = this.toMutableList()
        list.removeAt(0)
        return list
    }

    // Adding empty strings in order not to invoke ArrayIndexOutOfBoundsException
    private fun List<String>.addEmptyStrings(): List<String> {
        val row = this.toMutableList()
        repeat(MAX_COLUMNS_IN_ROW) {
            row.add("")
        }
        return row
    }
}