package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity)

    @Query("SELECT * FROM progress WHERE progressId = :progressId")
    fun getProgress(progressId: String): Flow<ProgressEntity?>

    @Query("SELECT * FROM progress WHERE studentId = :studentId AND topicId = :topicId")
    fun getProgressByTopic(studentId: String, topicId: String): Flow<ProgressEntity?>

    @Query("SELECT * FROM progress WHERE studentId = :studentId ORDER BY lastAccessedAt DESC LIMIT 10")
    fun getRecentProgress(studentId: String): Flow<List<ProgressEntity>>

    @Query("SELECT AVG(completionPercentage) FROM progress WHERE studentId = :studentId")
    fun getAverageProgress(studentId: String): Flow<Double?>

    @Query("SELECT AVG(accuracy) FROM progress WHERE studentId = :studentId")
    fun getAverageAccuracy(studentId: String): Flow<Double?>

    @Update
    suspend fun updateProgress(progress: ProgressEntity)

    @Query("DELETE FROM progress WHERE studentId = :studentId")
    suspend fun deleteStudentProgress(studentId: String)
}
