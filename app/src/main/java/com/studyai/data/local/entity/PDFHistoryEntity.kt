package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "pdf_history", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = TopicEntity::class, parentColumns = ["topicId"], childColumns = ["topicId"], onDelete = ForeignKey.SET_NULL)], indices = [Index("studentId"), Index("topicId"), Index("pdfId"), Index("createdAt")])
data class PDFHistoryEntity(
    @PrimaryKey val pdfId: String,
    val studentId: String,
    val topicId: String? = null,
    val pdfType: String,
    val fileName: String,
    val filePathUri: String,
    val fileSizeBytes: Long = 0,
    val title: String = "",
    val description: String = "",
    val author: String = "StudyAI",
    val pageCount: Int = 0,
    val contentSummary: String = "",
    val includesNotes: Boolean = false,
    val includesQuestions: Boolean = false,
    val includesDiagrams: Boolean = false,
    val downloadCount: Int = 0,
    val viewCount: Int = 0,
    val printCount: Int = 0,
    val lastViewedAt: Date? = null,
    val quality: String = "HIGH",
    val isStarred: Boolean = false,
    val syncStatus: String = "LOCAL",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val expiryDate: Date? = null
)
