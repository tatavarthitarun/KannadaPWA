package com.kannadapwa

import com.kannadapwa.domain.transliteration.TransliterationEngine
import com.kannadapwa.data.repository.ContentRepository
import com.kannadapwa.data.models.*

fun main() {
    println("🚀 Kannada PWA Starting...")
    println("🎨 Phase 3: Compose Multiplatform UI - Starting...")
    
    // Initialize our core systems
    val engine = TransliterationEngine()
    val repository = ContentRepository()
    
    println("✅ Core systems initialized successfully!")
    println("🎯 Building UI...")
    
    // For now, let's create a simple console-based UI demonstration
    createConsoleUI(engine, repository)
}

fun createConsoleUI(engine: TransliterationEngine, repository: ContentRepository) {
    println("\n🎨 Creating Console UI...")
    
    // Display the main header
    println("=" * 60)
    println("🎯 ಕನ್ನಡ PWA - Learn Kannada Language")
    println("🎯 తెలుగు మాతృభాషా వారికి - For Telugu Native Speakers")
    println("=" * 60)
    
    // Display vowels section
    displayVowelsSection(repository)
    
    // Display numbers section
    displayNumbersSection(repository)
    
    // Display lessons section
    displayLessonsSection(repository)
    
    // Display transliteration examples
    displayTransliterationExamples(engine)
    
    println("\n🎉 Phase 3: Console UI Complete!")
    println("🚀 Ready for Phase 4: Interactive Features")
    println("=" * 60)
}

fun displayVowelsSection(repository: ContentRepository) {
    println("\n📚 ಸ್ವರಗಳು - Vowels - స్వరాలు")
    println("-" * 40)
    
    val vowels = repository.getLettersByCategory(LetterCategory.VOWEL)
    vowels.take(5).forEach { vowel ->
        println("${vowel.kannada} -> ${vowel.telugu} -> ${vowel.english} (${vowel.pronunciation})")
    }
    println("... and ${vowels.size - 5} more vowels")
}

fun displayNumbersSection(repository: ContentRepository) {
    println("\n🔢 ಸಂಖ್ಯೆಗಳು - Numbers - సంఖ్యలు")
    println("-" * 40)
    
    val numbers = repository.getWordsByCategory(WordCategory.NUMBERS)
    numbers.forEach { number ->
        println("${number.kannada} -> ${number.telugu} -> ${number.english} (${number.meaning})")
    }
}

fun displayLessonsSection(repository: ContentRepository) {
    println("\n📖 ಪಾಠಗಳು - Lessons - పాఠాలు")
    println("-" * 40)
    
    val lessons = repository.getAllLessons()
    lessons.forEach { lesson ->
        println("📚 ${lesson.title}")
        println("   ${lesson.titleKannada}")
        println("   ⏱️  ${lesson.estimatedTime} minutes")
        println()
    }
}

fun displayTransliterationExamples(engine: TransliterationEngine) {
    println("\n🔄 Transliteration Examples")
    println("-" * 40)
    
    val testWords = listOf("ನಮಸ್ಕಾರ", "ಕನ್ನಡ", "ಭಾಷೆ", "ಕಲಿಕೆ")
    
    testWords.forEach { word ->
        println("Kannada: $word")
        println("Telugu:  ${engine.kannadaToTelugu(word)}")
        println("English:  ${engine.kannadaToEnglish(word)}")
        println()
    }
}

// Extension function to repeat strings
operator fun String.times(n: Int): String {
    return buildString {
        repeat(n) { append(this@times) }
    }
}
