package com.studyai.domain.model

import java.util.*

data class Lesson(
    val lessonId: String,
    val topicId: String,
    val chapterId: String,
    val subjectId: String,
    val lessonName: String,
    val lessonNumber: Int,
    val lessonType: String,
    val content: String = "",
    val contentSummary: String = "",
    val learningObjectives: List<String> = emptyList(),
    val estimatedMinutes: Int = 10,
    val difficulty: String = "Intermediate",
    val hasQuestions: Boolean = true,
    val hasExamples: Boolean = true,
    val isPublished: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
