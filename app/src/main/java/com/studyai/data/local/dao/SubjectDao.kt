package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<SubjectEntity>)

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE subjectId = :subjectId")
    fun getSubject(subjectId: String): Flow<SubjectEntity?>

    @Query("SELECT * FROM subjects WHERE category = :category ORDER BY orderIndex ASC")
    fun getSubjectsByCategory(category: String): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE isPopular = 1 ORDER BY orderIndex ASC")
    fun getPopularSubjects(): Flow<List<SubjectEntity>>

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
}
