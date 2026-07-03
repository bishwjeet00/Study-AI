package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.ChapterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: ChapterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chapters: List<ChapterEntity>)

    @Query("SELECT * FROM chapters WHERE chapterId = :chapterId")
    fun getChapter(chapterId: String): Flow<ChapterEntity?>

    @Query("SELECT * FROM chapters WHERE subjectId = :subjectId ORDER BY chapterNumber ASC")
    fun getChaptersBySubject(subjectId: String): Flow<List<ChapterEntity>>

    @Query("SELECT COUNT(*) FROM chapters WHERE subjectId = :subjectId")
    suspend fun getChapterCount(subjectId: String): Int

    @Update
    suspend fun updateChapter(chapter: ChapterEntity)

    @Delete
    suspend fun deleteChapter(chapter: ChapterEntity)
}
