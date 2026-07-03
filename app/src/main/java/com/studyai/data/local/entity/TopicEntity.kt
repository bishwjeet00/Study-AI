package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "topics", foreignKeys = [ForeignKey(entity = ChapterEntity::class, parentColumns = ["chapterId"], childColumns = ["chapterId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = SubjectEntity::class, parentColumns = ["subjectId"], childColumns = ["subjectId"], onDelete = ForeignKey.CASCADE)], indices = [Index("chapterId"), Index("subjectId")])
data class TopicEntity(
    @PrimaryKey val topicId: String,
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
    val notesUrl: String? = null,
    val quizUrl: String? = null,
    val videoUrl: String? = null,
    val isActive: Boolean = true,
    val isLocked: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
