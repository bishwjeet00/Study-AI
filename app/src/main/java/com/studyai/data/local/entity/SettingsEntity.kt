package com.studyai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "settings", foreignKeys = [ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"], childColumns = ["studentId"], onDelete = ForeignKey.CASCADE)], indices = [Index(value = ["studentId"], unique = true)])
data class SettingsEntity(
    @PrimaryKey val settingId: String,
    val studentId: String,
    val themeMode: String = "SYSTEM",
    val textSize: String = "MEDIUM",
    val fontFamily: String = "DEFAULT",
    val accentColor: String = "#6200EE",
    val notificationsEnabled: Boolean = true,
    val studyRemindersEnabled: Boolean = true,
    val revisionRemindersEnabled: Boolean = true,
    val quizRemindersEnabled: Boolean = true,
    val soundEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val voiceEnabled: Boolean = true,
    val voiceGender: String = "FEMALE",
    val voiceSpeed: Float = 1.0f,
    val voiceLanguage: String = "ENGLISH",
    val difficultyLevel: String = "INTERMEDIATE",
    val dailyGoalMinutes: Int = 30,
    val enableSpacedRepetition: Boolean = true,
    val enableActiveRecall: Boolean = true,
    val largeTextEnabled: Boolean = false,
    val highContrastEnabled: Boolean = false,
    val screenReaderEnabled: Boolean = false,
    val allowAnalytics: Boolean = true,
    val allowCrashReports: Boolean = true,
    val cloudSyncEnabled: Boolean = true,
    val autoBackupEnabled: Boolean = true,
    val batterySaverMode: Boolean = false,
    val dataSaverMode: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
