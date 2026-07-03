package com.studyai.domain.model

import java.util.*

data class Student(
    val studentId: String,
    val name: String,
    val email: String,
    val profilePicUrl: String? = null,
    val preferredLanguage: String = "English",
    val learningLevel: String = "Beginner",
    val dailyGoalMinutes: Int = 30,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
