package com.studyai.domain.model

import java.util.*

data class Topic(
    val topicId: String,
    val chapterId: String,
    val subjectId: String,
    val topicNumber: Int,
    val topicName: String,
    val topicDescription: String = "",
    val definition: String = "",
    val simpleExplanation: String = "",
    val hindiMeaning: String = "",
    val realLifeExample: String = "",
    val estimatedMinutes: Int = 30,
    val difficulty: String = "Intermediate",
    val hasGeneratedNotes: Boolean = false,
    val hasGeneratedQuiz: Boolean = false,
    val hasGeneratedPDF: Boolean = false,
    val isActive: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
