package ru.dillab.sportdiary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dillab.sportdiary.data.local.entity.DayResultEntity

@Database(entities = [DayResultEntity::class], version = 1)
abstract class DayResultsRoomDatabase : RoomDatabase() {

    abstract val dao: DayResultDao
}