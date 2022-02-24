package ru.dillab.sportdiary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.dillab.sportdiary.data.local.entity.EveningResultEntity
import ru.dillab.sportdiary.data.local.entity.MorningResultEntity

@Dao
interface DayResultDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(
        morningResults: List<MorningResultEntity>,
        eveningResults: List<EveningResultEntity>
    )

    @Insert(onConflict = REPLACE)
    suspend fun insertMorningResult(morningResult: MorningResultEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertEveningResult(eveningResult: EveningResultEntity)

    // @Query(
    //     "SELECT " +
    //             "id AS id, morning_time AS morningTime, sleep_hours AS sleepHours, pulse AS pulse, " +
    //             "muscle_pain AS musclePain " +
    //             "FROM morning_results LEFt JOIN evening_results USING(id) " +
    //             "UNION ALL SELECT " +
    //             "id AS id, evening_time AS eveningTime, productivity AS productivity, goals AS goals, " +
    //             "qualities AS qualities FROM evening_results LEFt JOIN morning_results USING(id) " +
    //             "WHERE morning_results.id IS NULL ORDER BY id DESC"
    // )
    // suspend fun getDayResults(): List<DayResult>

    @Query("SELECT * FROM morning_results")
    suspend fun getMorningResults(): List<MorningResultEntity>

    @Query("SELECT * FROM evening_results")
    suspend fun getEveningResults(): List<EveningResultEntity>

    @Query("SELECT * FROM morning_results WHERE id = :id")
    fun getMorningResultById(id: Int): Flow<MorningResultEntity?>

    @Query("SELECT * FROM evening_results WHERE id = :id")
    fun getEveningResultById(id: Int): Flow<EveningResultEntity?>
}