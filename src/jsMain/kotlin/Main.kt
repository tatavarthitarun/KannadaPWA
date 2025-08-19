package com.kannadapwa

import com.kannadapwa.domain.transliteration.TransliterationEngine
import com.kannadapwa.data.repository.ContentRepository
import com.kannadapwa.data.models.*

fun main() {
    println("🚀 Kannada PWA Starting...")
    println("🎨 Phase 6: Advanced UI Components - Starting...")

    // Initialize our core systems
    val engine = TransliterationEngine()
    val repository = ContentRepository()

    println("✅ Core systems initialized successfully!")
    println("🎯 Building Advanced UI Components...")

    // Initialize the advanced UI
    initializeAdvancedUI(engine, repository)
}

fun initializeAdvancedUI(engine: TransliterationEngine, repository: ContentRepository) {
    println("🎨 Initializing Advanced UI Components...")

    // Set up real-time transliteration
    setupRealTimeTransliteration(engine)
    
    // Populate interactive lessons
    populateInteractiveLessons(repository)
    
    // Populate interactive vowels
    populateInteractiveVowels(repository)
    
    // Initialize learning progress
    initializeLearningProgress(repository)
    
    // Set up event listeners
    setupEventListeners(engine, repository)
    
    println("✅ Phase 6: Advanced UI Components initialized successfully!")
    println("🚀 Your interactive Kannada learning interface is ready!")
    println("=".repeat(50))
}

fun setupRealTimeTransliteration(engine: TransliterationEngine) {
    println("🔄 Setting up real-time transliteration...")
    
    // This will be handled by JavaScript for real-time updates
    // The Kotlin engine provides the transliteration logic
}

fun populateInteractiveLessons(repository: ContentRepository) {
    println("📚 Populating interactive lessons...")
    
    val lessons = repository.getAllLessons()
    lessons.forEach { lesson ->
        println("📖 Lesson: ${lesson.title} (${lesson.titleKannada})")
        println("   ⏱️  ${lesson.estimatedTime} minutes")
        println("   🎯 Difficulty: ${lesson.difficulty}")
    }
}

fun populateInteractiveVowels(repository: ContentRepository) {
    println("🔤 Populating interactive vowels...")
    
    val vowels = repository.getLettersByCategory(LetterCategory.VOWEL)
    vowels.take(5).forEach { vowel ->
        println("🎯 ${vowel.kannada} -> ${vowel.telugu} -> ${vowel.english} (${vowel.pronunciation})")
    }
}

fun initializeLearningProgress(repository: ContentRepository) {
    println("📊 Initializing learning progress...")
    
    val totalLessons = repository.getAllLessons().size
    val totalVowels = repository.getLettersByCategory(LetterCategory.VOWEL).size
    
    println("📚 Total Lessons: $totalLessons")
    println("🔤 Total Vowels: $totalVowels")
    println("🎯 Progress tracking ready!")
}

fun setupEventListeners(engine: TransliterationEngine, repository: ContentRepository) {
    println("🎧 Setting up event listeners...")
    
    // These will be handled by JavaScript for real-time interaction
    // The Kotlin engine and repository provide the data and logic
}

// Extension function to get transliteration for a word
fun String.transliterateToTelugu(engine: TransliterationEngine): String {
    return engine.kannadaToTelugu(this)
}

fun String.transliterateToEnglish(engine: TransliterationEngine): String {
    return engine.kannadaToEnglish(this)
}

// Function to get pronunciation for a letter
fun Char.getPronunciationGuide(): String {
    return when (this) {
        'ಅ' -> "ah (like 'a' in 'about')"
        'ಆ' -> "aa (like 'a' in 'father')"
        'ಇ' -> "ee (like 'i' in 'bit')"
        'ಈ' -> "ee (like 'ee' in 'see')"
        'ಉ' -> "oo (like 'u' in 'put')"
        'ಊ' -> "oo (like 'oo' in 'moon')"
        'ಎ' -> "e (like 'e' in 'bed')"
        'ಏ' -> "e (like 'ay' in 'say')"
        'ಐ' -> "ai (like 'ai' in 'aisle')"
        'ಒ' -> "o (like 'o' in 'go')"
        'ಓ' -> "o (like 'o' in 'go')"
        'ಔ' -> "au (like 'ow' in 'cow')"
        else -> "Pronunciation guide not available"
    }
}

// Function to calculate learning progress
fun calculateProgress(completedLessons: Int, totalLessons: Int, learnedWords: Int, totalWords: Int): Int {
    val lessonProgress = (completedLessons.toFloat() / totalLessons.toFloat()) * 50
    val wordProgress = (learnedWords.toFloat() / totalWords.toFloat()) * 50
    return (lessonProgress + wordProgress).toInt()
}

// Function to get lesson difficulty color
fun getDifficultyColor(difficulty: String): String {
    return when (difficulty) {
        "BEGINNER" -> "#28a745" // Green
        "INTERMEDIATE" -> "#ffc107" // Yellow
        "ADVANCED" -> "#dc3545" // Red
        else -> "#6c757d" // Default gray
    }
}

// Function to format time duration
fun formatDuration(minutes: Int): String {
    return when {
        minutes < 60 -> "${minutes} min"
        minutes == 60 -> "1 hour"
        else -> "${minutes / 60}h ${minutes % 60}m"
    }
}
