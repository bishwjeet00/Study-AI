package com.studyai.service.voice

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * VoiceCommandParser - Parses and interprets voice commands
 * 
 * Commands supported:
 * - "Start Chapter 1"
 * - "Explain again"
 * - "Generate PDF"
 * - "Take Quiz"
 * - "Next Topic"
 * - "Bookmark this"
 * - etc.
 */
@Singleton
class VoiceCommandParser @Inject constructor() {

    data class ParsedCommand(
        val type: CommandType,
        val parameter: String? = null,
        val confidence: Float = 1.0f
    )

    enum class CommandType {
        START_CHAPTER,
        START_TOPIC,
        EXPLAIN_AGAIN,
        GENERATE_PDF,
        TAKE_QUIZ,
        READ_SUMMARY,
        NEXT_TOPIC,
        PREVIOUS_TOPIC,
        BOOKMARK,
        OPEN_REVISION,
        GET_PROGRESS,
        REPEAT_LAST,
        HELP,
        UNKNOWN
    }

    fun parseCommand(input: String): ParsedCommand {
        val lowerInput = input.lowercase().trim()

        return when {
            lowerInput.contains("start") && lowerInput.contains("chapter") ->
                ParsedCommand(CommandType.START_CHAPTER, extractNumber(lowerInput, "chapter"))
            
            lowerInput.contains("start") && lowerInput.contains("topic") ->
                ParsedCommand(CommandType.START_TOPIC, extractNumber(lowerInput, "topic"))
            
            lowerInput.contains("explain") && lowerInput.contains("again") ->
                ParsedCommand(CommandType.EXPLAIN_AGAIN)
            
            lowerInput.contains("generate") && lowerInput.contains("pdf") ->
                ParsedCommand(CommandType.GENERATE_PDF)
            
            lowerInput.contains("quiz") ->
                ParsedCommand(CommandType.TAKE_QUIZ)
            
            lowerInput.contains("summary") || lowerInput.contains("read") ->
                ParsedCommand(CommandType.READ_SUMMARY)
            
            lowerInput.contains("next") ->
                ParsedCommand(CommandType.NEXT_TOPIC)
            
            lowerInput.contains("previous") || lowerInput.contains("back") ->
                ParsedCommand(CommandType.PREVIOUS_TOPIC)
            
            lowerInput.contains("bookmark") || lowerInput.contains("save") ->
                ParsedCommand(CommandType.BOOKMARK)
            
            lowerInput.contains("revision") ->
                ParsedCommand(CommandType.OPEN_REVISION)
            
            lowerInput.contains("progress") || lowerInput.contains("completed") ->
                ParsedCommand(CommandType.GET_PROGRESS)
            
            lowerInput.contains("repeat") || lowerInput.contains("again") ->
                ParsedCommand(CommandType.REPEAT_LAST)
            
            lowerInput.contains("help") ->
                ParsedCommand(CommandType.HELP)
            
            else -> ParsedCommand(CommandType.UNKNOWN)
        }
    }

    private fun extractNumber(input: String, keyword: String): String? {
        val regex = "$keyword\\s+(\\d+)".toRegex()
        return regex.find(input)?.groupValues?.get(1)
    }
}
