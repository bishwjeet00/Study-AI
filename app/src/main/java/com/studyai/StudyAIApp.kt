package com.studyai

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * StudyAIApp - Application entry point
 * 
 * Initializes:
 * - Hilt dependency injection
 * - Timber logging
 * - Firebase crash reporting
 * - Global error handlers
 */
@HiltAndroidApp
class StudyAIApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize logging
        initializeLogging()
        
        // Configure crash reporting
        initializeCrashReporting()
        
        // Setup exception handler
        setupUncaughtExceptionHandler()
        
        Timber.i("StudyAI Application initialized successfully")
    }

    private fun initializeLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseLoggingTree())
        }
    }

    private fun initializeCrashReporting() {
        try {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
            FirebaseCrashlytics.getInstance().apply {
                setCustomKey("app_version", BuildConfig.VERSION_NAME)
                setCustomKey("device_manufacturer", android.os.Build.MANUFACTURER)
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize Firebase Crashlytics")
        }
    }

    private fun setupUncaughtExceptionHandler() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            Timber.e(exception, "Uncaught exception in thread: ${thread.name}")
            FirebaseCrashlytics.getInstance().recordException(exception)
            defaultHandler?.uncaughtException(thread, exception)
        }
    }

    private class ReleaseLoggingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority >= android.util.Log.ERROR) {
                android.util.Log.println(priority, tag, message)
                if (t != null) {
                    FirebaseCrashlytics.getInstance().recordException(t)
                }
            }
        }
    }
}
