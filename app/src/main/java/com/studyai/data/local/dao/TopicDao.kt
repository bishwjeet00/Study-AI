package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(topics: List<TopicEntity>)

    @Query("SELECT * FROM topics WHERE topicId = :topicId")
    fun getTopic(topicId: String): Flow<TopicEntity?>

    @Query("SELECT * FROM topics WHERE chapterId = :chapterId ORDER BY topicNumber ASC")
    fun getTopicsByChapter(chapterId: String): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topics WHERE subjectId = :subjectId ORDER BY topicNumber ASC")
    fun getTopicsBySubject(subjectId: String): Flow<List<TopicEntity>>

    @Query("SELECT COUNT(*) FROM topics WHERE chapterId = :chapterId")
    suspend fun getTopicCount(chapterId: String): Int

    @Update
    suspend fun updateTopic(topic: TopicEntity)

    @Delete
    suspend fun deleteTopic(topic: TopicEntity)
}
