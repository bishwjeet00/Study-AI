package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "learning_statistics", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE)], indices = [Index("studentId"), Index("statisticsDate"), Index(value = ["studentId", "statisticsDate"], unique = true)])
data class LearningStatisticsEntity(
    @PrimaryKey val statsId: String,
    val studentId: String,
    val statisticsDate: Date = Date(),
    val dailyStudyTimeMinutes: Int = 0,
    val sessionsCompletedToday: Int = 0,
    val lessonsCompletedToday: Int = 0,
    val topicsCompletedToday: Int = 0,
    val questionsAnsweredToday: Int = 0,
    val accuracyPercentage: Double = 0.0,
    val averageQuizScore: Double = 0.0,
    val averageTimePerQuestion: Int = 0,
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0,
    val skippedQuestions: Int = 0,
    val weeklyStudyTimeMinutes: Int = 0,
    val weeklyLessonsCompleted: Int = 0,
    val weeklyTopicsCompleted: Int = 0,
    val weeklyAverageAccuracy: Double = 0.0,
    val daysActiveThisWeek: Int = 0,
    val monthlyStudyTimeMinutes: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val favoriteSubject: String = "",
    val strongestTopic: String = "",
    val weakestTopic: String = "",
    val xpPointsEarned: Int = 0,
    val totalXpPoints: Int = 0,
    val currentLevel: Int = 1,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
