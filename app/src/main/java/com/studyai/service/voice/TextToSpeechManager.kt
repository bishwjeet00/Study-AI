package com.studyai.service.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TextToSpeechManager - Handles voice output
 * 
 * Features:
 * - Multiple voice styles (Male, Female)
 * - Speed and pitch control
 * - Multiple languages
 * - Offline support
 */
@Singleton
class TextToSpeechManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var textToSpeech: TextToSpeech? = null
    private var isInitialized = false
    private var listener: TextToSpeechListener? = null

    interface TextToSpeechListener {
        fun onSpeakStart()
        fun onSpeakDone()
        fun onSpeakError()
    }

    fun initialize(listener: TextToSpeechListener?) {
        this.listener = listener
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isInitialized = true
                textToSpeech?.language = Locale("en", "IN")
                textToSpeech?.setOnUtteranceProgressListener(UtteranceListener())
            } else {
                Timber.e("TextToSpeech initialization failed")
            }
        }
    }

    fun speak(text: String, language: String = "en-IN", speed: Float = 1.0f, pitch: Float = 1.0f) {
        if (!isInitialized || textToSpeech == null) {
            Timber.w("TextToSpeech not initialized")
            return
        }

        try {
            textToSpeech?.apply {
                setSpeechRate(speed)
                setPitch(pitch)
                val locale = when (language) {
                    "hi-IN" -> Locale("hi", "IN")
                    "en-IN" -> Locale("en", "IN")
                    else -> Locale("en", "IN")
                }
                language = locale
                speak(text, TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString())
            }
        } catch (e: Exception) {
            Timber.e(e, "Error speaking text")
        }
    }

    fun stop() {
        try {
            textToSpeech?.stop()
        } catch (e: Exception) {
            Timber.e(e, "Error stopping speech")
        }
    }

    fun pause() {
        try {
            textToSpeech?.stop()
        } catch (e: Exception) {
            Timber.e(e, "Error pausing speech")
        }
    }

    fun release() {
        try {
            textToSpeech?.shutdown()
            isInitialized = false
        } catch (e: Exception) {
            Timber.e(e, "Error releasing TextToSpeech")
        }
    }

    private inner class UtteranceListener : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {
            listener?.onSpeakStart()
        }

        override fun onDone(utteranceId: String?) {
            listener?.onSpeakDone()
        }

        override fun onError(utteranceId: String?) {
            listener?.onSpeakError()
        }
    }
}
