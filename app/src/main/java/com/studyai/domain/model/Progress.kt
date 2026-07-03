package com.studyai.domain.model

import java.util.*

data class Progress(
    val progressId: String,
    val studentId: String,
    val topicId: String,
    val lessonId: String? = null,
    val status: String = "NOT_STARTED",
    val completionPercentage: Double = 0.0,
    val isCompleted: Boolean = false,
    val totalTimeSpentSeconds: Long = 0,
    val sessionCount: Int = 0,
    val averageQuizScore: Double = 0.0,
    val accuracy: Double = 0.0,
    val lastAccessedAt: Date = Date(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
