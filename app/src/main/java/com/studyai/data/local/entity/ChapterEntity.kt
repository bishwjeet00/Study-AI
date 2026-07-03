package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "chapters", foreignKeys = [ForeignKey(entity = SubjectEntity::class, parentColumns = ["subjectId"], childColumns = ["subjectId"], onDelete = ForeignKey.CASCADE)], indices = [Index("subjectId")])
data class ChapterEntity(
    @PrimaryKey val chapterId: String,
    val subjectId: String,
    val chapterNumber: Int,
    val chapterName: String,
    val description: String = "",
    val learningObjectives: List<String> = emptyList(),
    val keyPoints: List<String> = emptyList(),
    val estimatedMinutes: Int = 0,
    val totalTopics: Int = 0,
    val totalLessons: Int = 0,
    val difficulty: String = "Intermediate",
    val thumbnailUrl: String? = null,
    val videoUrl: String? = null,
    val isActive: Boolean = true,
    val isLocked: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
