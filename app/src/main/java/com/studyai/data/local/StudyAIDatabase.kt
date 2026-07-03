package com.studyai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.studyai.data.local.dao.*
import com.studyai.data.local.entity.*

@Database(
    entities = [
        StudentEntity::class,
        SubjectEntity::class,
        ChapterEntity::class,
        TopicEntity::class,
        LessonEntity::class,
        ProgressEntity::class,
        ConversationEntity::class,
        QuizEntity::class,
        QuizAnswerEntity::class,
        BookmarkEntity::class,
        PDFHistoryEntity::class,
        LearningStatisticsEntity::class,
        SettingsEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverters::class)
abstract class StudyAIDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun chapterDao(): ChapterDao
    abstract fun topicDao(): TopicDao
    abstract fun lessonDao(): LessonDao
    abstract fun progressDao(): ProgressDao
    abstract fun conversationDao(): ConversationDao
    abstract fun quizDao(): QuizDao
    abstract fun quizAnswerDao(): QuizAnswerDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun pdfHistoryDao(): PDFHistoryDao
    abstract fun learningStatisticsDao(): LearningStatisticsDao
    abstract fun settingsDao(): SettingsDao
}
