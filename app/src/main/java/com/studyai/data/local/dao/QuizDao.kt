package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.QuizEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quizzes: List<QuizEntity>)

    @Query("SELECT * FROM quizzes WHERE quizId = :quizId")
    fun getQuiz(quizId: String): Flow<QuizEntity?>

    @Query("SELECT * FROM quizzes WHERE topicId = :topicId ORDER BY createdAt DESC")
    fun getQuizzesByTopic(topicId: String): Flow<List<QuizEntity>>

    @Query("SELECT * FROM quizzes WHERE studentId = :studentId ORDER BY createdAt DESC")
    fun getQuizzesByStudent(studentId: String): Flow<List<QuizEntity>>

    @Query("SELECT COUNT(*) FROM quizzes WHERE topicId = :topicId")
    suspend fun getQuizCount(topicId: String): Int

    @Update
    suspend fun updateQuiz(quiz: QuizEntity)

    @Delete
    suspend fun deleteQuiz(quiz: QuizEntity)
}
