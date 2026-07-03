package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students WHERE studentId = :studentId")
    fun getStudent(studentId: String): Flow<StudentEntity?>

    @Query("SELECT * FROM students WHERE email = :email")
    suspend fun getStudentByEmail(email: String): StudentEntity?

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query("SELECT COUNT(*) FROM students")
    suspend fun getStudentCount(): Int
}
