package com.studyai.domain.model

import java.util.*

data class Conversation(
    val conversationId: String,
    val studentId: String,
    val topicId: String? = null,
    val messageType: String,
    val messageText: String,
    val senderType: String,
    val senderName: String = "",
    val language: String = "English",
    val aiConfidenceScore: Double = 0.0,
    val responseTime: Long = 0,
    val isHelpful: Boolean? = null,
    val userRating: Int? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
