package com.studyai.domain.repository

import com.studyai.domain.model.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    suspend fun getLessons(topicId: String): Result<List<Lesson>>
    fun getLessonsByTopic(topicId: String): Flow<List<Lesson>>
    fun getLesson(lessonId: String): Flow<Lesson?>
    suspend fun markLessonComplete(lessonId: String, studentId: String): Result<Unit>
    suspend fun updateLesson(lesson: Lesson): Result<Unit>
}
