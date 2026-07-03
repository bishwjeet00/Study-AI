package com.studyai.service.voice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * VoiceAssistantService - Main service for voice interaction
 * 
 * Responsibilities:
 * - Manage STT and TTS
 * - Parse voice commands
 * - Coordinate with AI responses
 * - Handle foreground notification
 */
@AndroidEntryPoint
class VoiceAssistantService : Service() {

    @Inject
    lateinit var speechToTextManager: SpeechToTextManager

    @Inject
    lateinit var textToSpeechManager: TextToSpeechManager

    @Inject
    lateinit var voiceCommandParser: VoiceCommandParser

    override fun onCreate() {
        super.onCreate()
        Timber.d("VoiceAssistantService created")
        
        // Initialize speech managers
        initializeSpeechManagers()
    }

    private fun initializeSpeechManagers() {
        textToSpeechManager.initialize(object : TextToSpeechManager.TextToSpeechListener {
            override fun onSpeakStart() {
                Timber.d("Text to speech started")
            }

            override fun onSpeakDone() {
                Timber.d("Text to speech completed")
            }

            override fun onSpeakError() {
                Timber.e("Text to speech error")
            }
        })

        speechToTextManager.initialize(object : SpeechToTextManager.SpeechRecognitionListener {
            override fun onSpeechStart() {
                Timber.d("Speech recognition started")
            }

            override fun onSpeechResult(result: String) {
                Timber.d("Speech result: $result")
                handleVoiceCommand(result)
            }

            override fun onSpeechError(error: String) {
                Timber.e("Speech error: $error")
                textToSpeechManager.speak("Sorry, I didn't understand. Please try again.")
            }

            override fun onSpeechEnd() {
                Timber.d("Speech recognition ended")
            }
        })
    }

    private fun handleVoiceCommand(voiceInput: String) {
        val command = voiceCommandParser.parseCommand(voiceInput)
        Timber.d("Parsed command: ${command.type}")
        
        // Handle command based on type
        when (command.type) {
            VoiceCommandParser.CommandType.START_CHAPTER -> {
                textToSpeechManager.speak("Starting chapter ${command.parameter}")
            }
            VoiceCommandParser.CommandType.EXPLAIN_AGAIN -> {
                textToSpeechManager.speak("Let me explain that again.")
            }
            VoiceCommandParser.CommandType.TAKE_QUIZ -> {
                textToSpeechManager.speak("Let's take a quiz to test your knowledge.")
            }
            else -> {
                textToSpeechManager.speak("Command not recognized. Please try again.")
            }
        }
    }

    fun startListening(language: String = "en-IN") {
        speechToTextManager.startListening(language)
    }

    fun stopListening() {
        speechToTextManager.stopListening()
    }

    fun speak(text: String, language: String = "en-IN") {
        textToSpeechManager.speak(text, language)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        speechToTextManager.release()
        textToSpeechManager.release()
        Timber.d("VoiceAssistantService destroyed")
    }
}
