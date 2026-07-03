package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val studentId: String,
    val name: String,
    val email: String,
    val profilePicUrl: String? = null,
    val preferredLanguage: String = "English",
    val learningLevel: String = "Beginner",
    val dailyGoalMinutes: Int = 30,
    val aiVoiceGender: String = "Female",
    val aiTeachingStyle: String = "Formal",
    val enableVoiceAssistant: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val isActive: Boolean = true
)
