package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: SettingsEntity)

    @Query("SELECT * FROM settings WHERE studentId = :studentId")
    fun getSettings(studentId: String): Flow<SettingsEntity?>

    @Update
    suspend fun updateSettings(settings: SettingsEntity)

    @Delete
    suspend fun deleteSettings(settings: SettingsEntity)
}
