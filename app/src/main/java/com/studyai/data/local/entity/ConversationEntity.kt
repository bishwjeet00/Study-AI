package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "conversations", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = TopicEntity::class, parentColumns = ["topicId"], childColumns = ["topicId"], onDelete = ForeignKey.SET_NULL), ForeignKey(entity = LessonEntity::class, parentColumns = ["lessonId"], childColumns = ["lessonId"], onDelete = ForeignKey.SET_NULL)], indices = [Index("studentId"), Index("topicId"), Index("conversationId"), Index("createdAt")])
data class ConversationEntity(
    @PrimaryKey val conversationId: String,
    val studentId: String,
    val topicId: String? = null,
    val lessonId: String? = null,
    val messageType: String,
    val messageText: String,
    val senderType: String,
    val senderName: String = "",
    val language: String = "English",
    val tone: String = "Neutral",
    val messageLength: Int = 0,
    val hasAttachment: Boolean = false,
    val attachmentUrl: String? = null,
    val aiConfidenceScore: Double = 0.0,
    val aiModel: String = "gemini-pro",
    val responseTime: Long = 0,
    val isHelpful: Boolean? = null,
    val userRating: Int? = null,
    val parentMessageId: String? = null,
    val conversationSessionId: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
