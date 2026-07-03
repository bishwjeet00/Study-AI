package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.QuizAnswerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizAnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: QuizAnswerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(answers: List<QuizAnswerEntity>)

    @Query("SELECT * FROM quiz_answers WHERE answerId = :answerId")
    fun getAnswer(answerId: String): Flow<QuizAnswerEntity?>

    @Query("SELECT * FROM quiz_answers WHERE quizId = :quizId ORDER BY createdAt DESC")
    fun getAnswersByQuiz(quizId: String): Flow<List<QuizAnswerEntity>>

    @Query("SELECT * FROM quiz_answers WHERE studentId = :studentId AND quizId = :quizId AND attemptNumber = :attemptNumber")
    fun getAnswersByAttempt(studentId: String, quizId: String, attemptNumber: Int): Flow<List<QuizAnswerEntity>>

    @Query("SELECT AVG(CAST(marksObtained as FLOAT)) / AVG(CAST(totalMarks as FLOAT)) * 100 FROM quiz_answers WHERE quizId = :quizId")
    fun getAverageScoreForQuiz(quizId: String): Flow<Double?>

    @Query("SELECT COUNT(*) FROM quiz_answers WHERE isCorrect = 1 AND studentId = :studentId")
    suspend fun getCorrectAnswersCount(studentId: String): Int

    @Update
    suspend fun updateAnswer(answer: QuizAnswerEntity)
}
