package com.studyai.utils

/**
 * Constants - App-wide constants
 */
object Constants {
    // Database
    const val DATABASE_NAME = "studyai_database"
    
    // API
    const val BASE_URL = "https://api.studyai.com/"
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    
    // Preferences
    const val PREF_STUDENT_ID = "student_id"
    const val PREF_FIRST_LAUNCH = "first_launch"
    const val PREF_THEME = "theme"
    
    // Learning
    const val MIN_STUDY_TIME = 5 // minutes
    const val MAX_QUIZ_TIME = 60 // minutes
    const val DEFAULT_DAILY_GOAL = 30 // minutes
    
    // Voice
    const val VOICE_LANGUAGE_ENGLISH = "en-IN"
    const val VOICE_LANGUAGE_HINDI = "hi-IN"
    const val DEFAULT_VOICE_SPEED = 1.0f
    const val DEFAULT_VOICE_PITCH = 1.0f
    
    // Subjects
    val SUPPORTED_SUBJECTS = listOf(
        "Physics", "Chemistry", "Biology", "Mathematics",
        "Psychology", "Economics", "English", "Hindi",
        "History", "Geography", "Political Science", "Computer Science"
    )
    
    // Learning Modes
    val LEARNING_MODES = listOf(
        "Beginner", "School", "Board Exam", "Deep Concept",
        "Fast Revision", "Quiz", "Viva", "Interview",
        "Doubt Solving", "Voice Tutor", "Crash Course", "Practice"
    )
}
