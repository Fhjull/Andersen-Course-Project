package ru.dillab.sportdiary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.data.local.entity.DayResultEntity

@Dao
interface DayResultDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(dayResult: DayResultEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(listOfDayResults: List<DayResultEntity>)

    @Update
    suspend fun update(dayResult: DayResultEntity): Int

    @Query("SELECT * FROM day_results ORDER BY id DESC")
    suspend fun getDayResults(): List<DayResultEntity>

    @Query("SELECT * FROM day_results WHERE id = :id")
    suspend fun getById(id: Int): DayResultEntity
}