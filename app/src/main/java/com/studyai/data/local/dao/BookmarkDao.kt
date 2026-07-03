package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmarks WHERE bookmarkId = :bookmarkId")
    fun getBookmark(bookmarkId: String): Flow<BookmarkEntity?>

    @Query("SELECT * FROM bookmarks WHERE studentId = :studentId ORDER BY isPinned DESC, updatedAt DESC")
    fun getBookmarksByStudent(studentId: String): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE studentId = :studentId AND folderName = :folderName ORDER BY updatedAt DESC")
    fun getBookmarksByFolder(studentId: String, folderName: String): Flow<List<BookmarkEntity>>

    @Query("SELECT DISTINCT folderName FROM bookmarks WHERE studentId = :studentId")
    fun getFolders(studentId: String): Flow<List<String>>

    @Update
    suspend fun updateBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE studentId = :studentId")
    suspend fun deleteStudentBookmarks(studentId: String)
}
