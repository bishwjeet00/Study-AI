package com.studyai.data.local.dao

import androidx.room.*
import com.studyai.data.local.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(conversation: ConversationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversations: List<ConversationEntity>)

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId")
    fun getMessage(conversationId: String): Flow<ConversationEntity?>

    @Query("SELECT * FROM conversations WHERE studentId = :studentId AND topicId = :topicId ORDER BY createdAt DESC LIMIT 50")
    fun getConversationsByTopic(studentId: String, topicId: String): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE studentId = :studentId ORDER BY createdAt DESC")
    fun getAllConversations(studentId: String): Flow<List<ConversationEntity>>

    @Query("SELECT COUNT(*) FROM conversations WHERE studentId = :studentId")
    suspend fun getMessageCount(studentId: String): Int

    @Update
    suspend fun updateMessage(conversation: ConversationEntity)

    @Delete
    suspend fun deleteMessage(conversation: ConversationEntity)
}
