package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "progress", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = TopicEntity::class, parentColumns = ["topicId"], childColumns = ["topicId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = LessonEntity::class, parentColumns = ["lessonId"], childColumns = ["lessonId"], onDelete = ForeignKey.SET_NULL)], indices = [Index("studentId"), Index("topicId"), Index("lessonId"), Index(value = ["studentId", "topicId"], unique = true)])
data class ProgressEntity(
    @PrimaryKey val progressId: String,
    val studentId: String,
    val topicId: String,
    val lessonId: String? = null,
    val status: String = "NOT_STARTED",
    val completionPercentage: Double = 0.0,
    val isCompleted: Boolean = false,
    val firstAccessedAt: Date? = null,
    val lastAccessedAt: Date = Date(),
    val totalTimeSpentSeconds: Long = 0,
    val sessionCount: Int = 0,
    val averageQuizScore: Double = 0.0,
    val highestQuizScore: Double = 0.0,
    val lowestQuizScore: Double = 0.0,
    val totalQuizzesAttempted: Int = 0,
    val questionsAnsweredCorrectly: Int = 0,
    val accuracy: Double = 0.0,
    val revisitCount: Int = 0,
    val isFavorite: Boolean = false,
    val needsRevision: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
