package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookmarks", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = TopicEntity::class, parentColumns = ["topicId"], childColumns = ["topicId"], onDelete = ForeignKey.SET_NULL), ForeignKey(entity = LessonEntity::class, parentColumns = ["lessonId"], childColumns = ["lessonId"], onDelete = ForeignKey.SET_NULL)], indices = [Index("studentId"), Index("topicId"), Index("lessonId"), Index("bookmarkId")])
data class BookmarkEntity(
    @PrimaryKey val bookmarkId: String,
    val studentId: String,
    val topicId: String? = null,
    val lessonId: String? = null,
    val bookmarkType: String,
    val bookmarkName: String,
    val bookmarkDescription: String = "",
    val bookmarkColor: String = "#FF5722",
    val contentTitle: String = "",
    val contentSnippet: String = "",
    val contentUrl: String? = null,
    val thumbnailUrl: String? = null,
    val folderName: String = "General",
    val tags: List<String> = emptyList(),
    val category: String = "",
    val isFavorite: Boolean = false,
    val isPinned: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val lastViewedAt: Date = Date()
)
