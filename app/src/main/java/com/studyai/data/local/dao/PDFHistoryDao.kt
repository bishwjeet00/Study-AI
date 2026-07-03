package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.PDFHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PDFHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPDF(pdf: PDFHistoryEntity)

    @Query("SELECT * FROM pdf_history WHERE pdfId = :pdfId")
    fun getPDF(pdfId: String): Flow<PDFHistoryEntity?>

    @Query("SELECT * FROM pdf_history WHERE studentId = :studentId ORDER BY createdAt DESC")
    fun getPDFHistoryByStudent(studentId: String): Flow<List<PDFHistoryEntity>>

    @Query("SELECT * FROM pdf_history WHERE studentId = :studentId AND pdfType = :pdfType ORDER BY createdAt DESC")
    fun getPDFsByType(studentId: String, pdfType: String): Flow<List<PDFHistoryEntity>>

    @Query("SELECT * FROM pdf_history WHERE studentId = :studentId AND isStarred = 1 ORDER BY createdAt DESC")
    fun getStarredPDFs(studentId: String): Flow<List<PDFHistoryEntity>>

    @Update
    suspend fun updatePDF(pdf: PDFHistoryEntity)

    @Delete
    suspend fun deletePDF(pdf: PDFHistoryEntity)

    @Query("DELETE FROM pdf_history WHERE studentId = :studentId AND expiryDate < datetime('now')")
    suspend fun deleteExpiredPDFs(studentId: String)
}
