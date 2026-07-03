package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey val subjectId: String,
    val subjectName: String,
    val description: String = "",
    val iconUrl: String? = null,
    val color: String = "#000000",
    val difficulty: String = "Intermediate",
    val totalChapters: Int = 0,
    val estimatedHours: Int = 0,
    val boardExam: String = "CBSE",
    val grade: String = "Class 10",
    val isAvailable: Boolean = true,
    val isPopular: Boolean = false,
    val isFree: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val orderIndex: Int = 0,
    val category: String = "Science"
)
