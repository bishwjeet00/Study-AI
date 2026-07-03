package com.studyai.domain.repository

import com.studyai.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun createStudent(student: Student): Result<Student>
    fun getStudent(studentId: String): Flow<Student?>
    fun getStudentByEmail(email: String): Flow<Student?>
    suspend fun updateStudent(student: Student): Result<Unit>
    suspend fun deleteStudent(studentId: String): Result<Unit>
}
