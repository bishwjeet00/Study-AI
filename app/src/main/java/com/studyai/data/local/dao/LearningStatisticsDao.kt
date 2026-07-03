package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.LearningStatisticsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningStatisticsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatistics(stats: LearningStatisticsEntity)

    @Query("SELECT * FROM learning_statistics WHERE statsId = :statsId")
    fun getStatistics(statsId: String): Flow<LearningStatisticsEntity?>

    @Query("SELECT * FROM learning_statistics WHERE studentId = :studentId ORDER BY statisticsDate DESC LIMIT 1")
    fun getTodayStatistics(studentId: String): Flow<LearningStatisticsEntity?>

    @Query("SELECT * FROM learning_statistics WHERE studentId = :studentId ORDER BY statisticsDate DESC")
    fun getAllStatistics(studentId: String): Flow<List<LearningStatisticsEntity>>

    @Query("SELECT AVG(dailyStudyTimeMinutes) FROM learning_statistics WHERE studentId = :studentId AND statisticsDate >= datetime('now', '-7 days')")
    fun getWeeklyAverageStudyTime(studentId: String): Flow<Double?>

    @Query("SELECT AVG(accuracyPercentage) FROM learning_statistics WHERE studentId = :studentId")
    fun getOverallAccuracy(studentId: String): Flow<Double?>

    @Update
    suspend fun updateStatistics(stats: LearningStatisticsEntity)
}
