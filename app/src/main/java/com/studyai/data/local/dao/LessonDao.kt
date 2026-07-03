package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lessons: List<LessonEntity>)

    @Query("SELECT * FROM lessons WHERE lessonId = :lessonId")
    fun getLesson(lessonId: String): Flow<LessonEntity?>

    @Query("SELECT * FROM lessons WHERE topicId = :topicId ORDER BY lessonNumber ASC")
    fun getLessonsByTopic(topicId: String): Flow<List<LessonEntity>>

    @Query("SELECT COUNT(*) FROM lessons WHERE topicId = :topicId")
    suspend fun getLessonCount(topicId: String): Int

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)
}
