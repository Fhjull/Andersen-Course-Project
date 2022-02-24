package ru.dillab.sportdiary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity

@Database(entities = [MorningResultEntity::class, EveningResultEntity::class], version = 1)
abstract class DayResultsDatabase : RoomDatabase() {

    abstract val dao: DayResultDao
}