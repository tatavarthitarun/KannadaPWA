package com.kannadapwa.data.models

import kotlinx.serialization.Serializable

/**
 * Represents a Kannada letter with its transliterations
 */
@Serializable
data class KannadaLetter(
    val id: String,
    val kannada: String,
    val telugu: String,
    val english: String,
    val pronunciation: String,
    val category: LetterCategory,
    val examples: List<String> = emptyList()
)

/**
 * Categories of Kannada letters
 */
enum class LetterCategory {
    VOWEL,          // ಸ್ವರಗಳು (Swaragalu)
    CONSONANT,      // ವ್ಯಂಜನಗಳು (Vyanjanagalu)
    CONJUNCT        // ಸಂಯುಕ್ತಾಕ್ಷರಗಳು (Samyuktaksharagalu)
}

/**
 * Represents a Kannada word with transliterations
 */
@Serializable
data class KannadaWord(
    val id: String,
    val kannada: String,
    val telugu: String,
    val english: String,
    val pronunciation: String,
    val meaning: String,
    val category: WordCategory,
    val difficulty: Difficulty = Difficulty.BEGINNER
)

/**
 * Categories of Kannada words
 */
enum class WordCategory {
    NUMBERS,        // ಸಂಖ್ಯೆಗಳು (Sankhyegalu)
    COLORS,         // ಬಣ್ಣಗಳು (Bannagalu)
    FAMILY,         // ಕುಟುಂಬ (Kutumba)
    GREETINGS,      // ನಮಸ್ಕಾರಗಳು (Namaskaragalu)
    COMMON_OBJECTS, // ಸಾಮಾನ್ಯ ವಸ್ತುಗಳು (Samanya Vastugalu)
    BASIC_PHRASES   // ಮೂಲ ವಾಕ್ಯಗಳು (Mula Vakyagalu)
}

/**
 * Difficulty levels for learning content
 */
enum class Difficulty {
    BEGINNER,       // ಆರಂಭಿಕ (Arambhika)
    INTERMEDIATE,   // ಮಧ್ಯಮ (Madhyama)
    ADVANCED        // ಪ್ರಗತ (Pragata)
}

/**
 * Represents a learning lesson
 */
@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val titleKannada: String,
    val description: String,
    val content: List<LessonContent>,
    val difficulty: Difficulty,
    val estimatedTime: Int // in minutes
)

/**
 * Content within a lesson
 */
@Serializable
sealed class LessonContent {
    @Serializable
    data class LetterContent(val letter: KannadaLetter) : LessonContent()
    
    @Serializable
    data class WordContent(val word: KannadaWord) : LessonContent()
    
    @Serializable
    data class QuizContent(val quiz: Quiz) : LessonContent()
}

/**
 * Quiz for learning assessment
 */
@Serializable
data class Quiz(
    val id: String,
    val question: String,
    val questionKannada: String,
    val options: List<QuizOption>,
    val correctAnswerId: String,
    val explanation: String
)

/**
 * Quiz option
 */
@Serializable
data class QuizOption(
    val id: String,
    val text: String,
    val textKannada: String
)

/**
 * User learning progress
 */
@Serializable
data class LearningProgress(
    val userId: String,
    val completedLessons: Set<String> = emptySet(),
    val quizScores: Map<String, Int> = emptyMap(), // lessonId to score
    val lastStudied: Long = 0L,
    val totalStudyTime: Long = 0 // in minutes
)
