package com.studyai.service.voice

import android.content.Context
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * SpeechToTextManager - Handles voice input recognition
 * 
 * Features:
 * - Real-time speech recognition
 * - Multiple language support
 * - Error handling
 * - Offline capabilities (future)
 */
@Singleton
class SpeechToTextManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var speechRecognizer: SpeechRecognizer? = null
    private var listener: SpeechRecognitionListener? = null

    interface SpeechRecognitionListener {
        fun onSpeechStart()
        fun onSpeechResult(result: String)
        fun onSpeechError(error: String)
        fun onSpeechEnd()
    }

    fun initialize(listener: SpeechRecognitionListener) {
        this.listener = listener
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            speechRecognizer?.setRecognitionListener(RecognitionListenerImpl())
        }
    }

    fun startListening(language: String = "en-IN") {
        try {
            val intent = android.content.Intent(android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE, language)
            intent.putExtra(android.speech.RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            Timber.e(e, "Error starting speech recognition")
            listener?.onSpeechError(e.message ?: "Unknown error")
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
        } catch (e: Exception) {
            Timber.e(e, "Error stopping speech recognition")
        }
    }

    fun cancel() {
        try {
            speechRecognizer?.cancel()
        } catch (e: Exception) {
            Timber.e(e, "Error canceling speech recognition")
        }
    }

    fun release() {
        try {
            speechRecognizer?.destroy()
            speechRecognizer = null
        } catch (e: Exception) {
            Timber.e(e, "Error releasing speech recognizer")
        }
    }

    private inner class RecognitionListenerImpl : RecognitionListener {
        override fun onReadyForSpeech(params: android.os.Bundle?) {
            listener?.onSpeechStart()
        }

        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}

        override fun onEndOfSpeech() {
            listener?.onSpeechEnd()
        }

        override fun onError(error: Int) {
            val errorMessage = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "Audio error"
                SpeechRecognizer.ERROR_CLIENT -> "Client error"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Permissions required"
                SpeechRecognizer.ERROR_NETWORK -> "Network error"
                SpeechRecognizer.ERROR_NO_MATCH -> "No match found"
                SpeechRecognizer.ERROR_SERVER -> "Server error"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Speech timeout"
                else -> "Unknown error"
            }
            listener?.onSpeechError(errorMessage)
        }

        override fun onResults(results: android.os.Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (matches != null && matches.isNotEmpty()) {
                listener?.onSpeechResult(matches[0])
            }
        }

        override fun onPartialResults(partialResults: android.os.Bundle?) {}
        override fun onEvent(eventType: Int, params: android.os.Bundle?) {}
    }
}
