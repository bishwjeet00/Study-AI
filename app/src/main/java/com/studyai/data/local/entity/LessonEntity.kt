package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "lessons", foreignKeys = [ForeignKey(entity = TopicEntity::class, parentColumns = ["topicId"], childColumns = ["topicId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = ChapterEntity::class, parentColumns = ["chapterId"], childColumns = ["chapterId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = SubjectEntity::class, parentColumns = ["subjectId"], childColumns = ["subjectId"], onDelete = ForeignKey.CASCADE)], indices = [Index("topicId"), Index("chapterId"), Index("subjectId")])
data class LessonEntity(
    @PrimaryKey val lessonId: String,
    val topicId: String,
    val chapterId: String,
    val subjectId: String,
    val lessonName: String,
    val lessonNumber: Int,
    val lessonType: String,
    val content: String = "",
    val contentSummary: String = "",
    val learningObjectives: List<String> = emptyList(),
    val keyTerms: List<String> = emptyList(),
    val videoUrl: String? = null,
    val audioUrl: String? = null,
    val diagramUrl: String? = null,
    val estimatedMinutes: Int = 10,
    val difficulty: String = "Intermediate",
    val hasQuestions: Boolean = true,
    val hasExamples: Boolean = true,
    val isPublished: Boolean = true,
    val isActive: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
