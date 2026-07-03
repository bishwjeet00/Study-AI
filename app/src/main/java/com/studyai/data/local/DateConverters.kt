package com.studyai.data.local

import androidx.room.TypeConverter
import java.util.*

class DateConverters {
    @TypeConverter
    fun dateToTimestamp(value: Date?): Long? = value?.time

    @TypeConverter
    fun timestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun stringListToJson(value: List<String>?): String? {
        return value?.joinToString(",", "[", "]") { item -> "\"$item\"" }
    }

    @TypeConverter
    fun jsonToStringList(value: String?): List<String> {
        if (value == null) return emptyList()
        return try {
            value.trim().removeSurrounding("[", "]").split(",")
                .map { it.trim().removeSurrounding("\"") }.filter { it.isNotEmpty() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun doubleListToString(value: List<Double>?): String? = value?.joinToString(",")

    @TypeConverter
    fun stringToDoubleList(value: String?): List<Double> {
        if (value == null || value.isEmpty()) return emptyList()
        return try {
            value.split(",").mapNotNull { it.trim().toDoubleOrNull() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
