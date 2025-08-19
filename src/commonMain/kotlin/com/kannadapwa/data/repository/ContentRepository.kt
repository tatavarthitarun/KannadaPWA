package com.kannadapwa.data.repository

import com.kannadapwa.data.models.*
import com.kannadapwa.domain.transliteration.TransliterationEngine

/**
 * Repository for managing Kannada learning content
 */
class ContentRepository {
    
    private val transliterationEngine = TransliterationEngine()
    
    /**
     * Get all Kannada letters
     */
    fun getAllLetters(): List<KannadaLetter> = kannadaLetters
    
    /**
     * Get letters by category
     */
    fun getLettersByCategory(category: LetterCategory): List<KannadaLetter> {
        return kannadaLetters.filter { it.category == category }
    }
    
    /**
     * Get all Kannada words
     */
    fun getAllWords(): List<KannadaWord> = kannadaWords
    
    /**
     * Get words by category
     */
    fun getWordsByCategory(category: WordCategory): List<KannadaWord> {
        return kannadaWords.filter { it.category == category }
    }
    
    /**
     * Get words by difficulty
     */
    fun getWordsByDifficulty(difficulty: Difficulty): List<KannadaWord> {
        return kannadaWords.filter { it.difficulty == difficulty }
    }
    
    /**
     * Get all lessons
     */
    fun getAllLessons(): List<Lesson> = lessons
    
    /**
     * Get lesson by ID
     */
    fun getLessonById(id: String): Lesson? {
        return lessons.find { it.id == id }
    }
    
    /**
     * Search content by text
     */
    fun searchContent(query: String): List<Any> {
        val results = mutableListOf<Any>()
        
        // Search in letters
        results.addAll(kannadaLetters.filter { 
            it.kannada.contains(query) || 
            it.telugu.contains(query) || 
            it.english.contains(query) 
        })
        
        // Search in words
        results.addAll(kannadaWords.filter { 
            it.kannada.contains(query) || 
            it.telugu.contains(query) || 
            it.english.contains(query) ||
            it.meaning.contains(query, ignoreCase = true)
        })
        
        return results
    }
    
    companion object {
        // Sample Kannada letters data
        private val kannadaLetters = listOf(
            // Vowels (ಸ್ವರಗಳು)
            KannadaLetter("v1", "ಅ", "అ", "a", "ah", LetterCategory.VOWEL, listOf("ಅಮ್ಮ", "ಅಪ್ಪ")),
            KannadaLetter("v2", "ಆ", "ఆ", "ā", "aa", LetterCategory.VOWEL, listOf("ಆಕಾಶ", "ಆನೆ")),
            KannadaLetter("v3", "ಇ", "ఇ", "i", "ee", LetterCategory.VOWEL, listOf("ಇಲಿ", "ಇದು")),
            KannadaLetter("v4", "ಈ", "ఈ", "ī", "ee", LetterCategory.VOWEL, listOf("ಈಗ", "ಈರುಳ್ಳಿ")),
            KannadaLetter("v5", "ಉ", "ఉ", "u", "oo", LetterCategory.VOWEL, listOf("ಉಪ್ಪು", "ಉಳಿದ")),
            KannadaLetter("v6", "ಊ", "ఊ", "ū", "oo", LetterCategory.VOWEL, listOf("ಊರು", "ಊಟ")),
            KannadaLetter("v7", "ಋ", "ఋ", "ṛ", "ri", LetterCategory.VOWEL, listOf("ಋತು", "ಋಷಿ")),
            KannadaLetter("v8", "ಎ", "ఎ", "e", "e", LetterCategory.VOWEL, listOf("ಎಲೆ", "ಎಲ್ಲ")),
            KannadaLetter("v9", "ಏ", "ఏ", "ē", "ay", LetterCategory.VOWEL, listOf("ಏನು", "ಏಳು")),
            KannadaLetter("v10", "ಐ", "ఐ", "ai", "ai", LetterCategory.VOWEL, listOf("ಐದು", "ಐಶ್ವರ್ಯ")),
            KannadaLetter("v11", "ಒ", "ఒ", "o", "o", LetterCategory.VOWEL, listOf("ಒಂದು", "ಒಳಗೆ")),
            KannadaLetter("v12", "ಓ", "ఓ", "ō", "o", LetterCategory.VOWEL, listOf("ಓದು", "ಓಡು")),
            KannadaLetter("v13", "ಔ", "ఔ", "au", "au", LetterCategory.VOWEL, listOf("ಔಷಧ", "ಔಚಿತ್ಯ")),
            
            // Consonants (ವ್ಯಂಜನಗಳು)
            KannadaLetter("c1", "ಕ", "క", "ka", "ka", LetterCategory.CONSONANT, listOf("ಕಮಲ", "ಕಾಫಿ")),
            KannadaLetter("c2", "ಖ", "ఖ", "kha", "kha", LetterCategory.CONSONANT, listOf("ಖರೀದಿ", "ಖುಷಿ")),
            KannadaLetter("c3", "ಗ", "గ", "ga", "ga", LetterCategory.CONSONANT, listOf("ಗಣೇಶ", "ಗಾಡಿ")),
            KannadaLetter("c4", "ಘ", "ఘ", "gha", "gha", LetterCategory.CONSONANT, listOf("ಘಂಟೆ", "ಘನ")),
            KannadaLetter("c5", "ಚ", "చ", "ca", "cha", LetterCategory.CONSONANT, listOf("ಚಂದ್ರ", "ಚಹಾ")),
            KannadaLetter("c6", "ಛ", "ఛ", "cha", "chha", LetterCategory.CONSONANT, listOf("ಛತ್ರ", "ಛಲ")),
            KannadaLetter("c7", "ಜ", "జ", "ja", "ja", LetterCategory.CONSONANT, listOf("ಜನರು", "ಜೇನು")),
            KannadaLetter("c8", "ಝ", "ఝ", "jha", "jha", LetterCategory.CONSONANT, listOf("ಝಂಕಾರ", "ಝಗ")),
            KannadaLetter("c9", "ಟ", "ట", "ṭa", "ta", LetterCategory.CONSONANT, listOf("ಟೀಕೆ", "ಟಾಕು")),
            KannadaLetter("c10", "ಠ", "ఠ", "ṭha", "tha", LetterCategory.CONSONANT, listOf("ಠಾಣೆ", "ಠೇವಣಿ")),
            KannadaLetter("c11", "ಡ", "డ", "ḍa", "da", LetterCategory.CONSONANT, listOf("ಡಬ್ಬು", "ಡಾಕು")),
            KannadaLetter("c12", "ಢ", "ఢ", "ḍha", "dha", LetterCategory.CONSONANT, listOf("ಢಮಾಕು", "ಢಾಕೆ")),
            KannadaLetter("c13", "ಣ", "ణ", "ṇa", "na", LetterCategory.CONSONANT, listOf("ಣಮ", "ಣಾಕು")),
            KannadaLetter("c14", "ತ", "త", "ta", "ta", LetterCategory.CONSONANT, listOf("ತಾಯಿ", "ತಾತ")),
            KannadaLetter("c15", "ಥ", "థ", "tha", "tha", LetterCategory.CONSONANT, listOf("ಥಳ", "ಥರ")),
            KannadaLetter("c16", "ದ", "ద", "da", "da", LetterCategory.CONSONANT, listOf("ದಾರಿ", "ದಿನ")),
            KannadaLetter("c17", "ಧ", "ధ", "dha", "dha", LetterCategory.CONSONANT, listOf("ಧನ", "ಧರ್ಮ")),
            KannadaLetter("c18", "ನ", "న", "na", "na", LetterCategory.CONSONANT, listOf("ನೀರು", "ನಾಯಿ")),
            KannadaLetter("c19", "ಪ", "ప", "pa", "pa", LetterCategory.CONSONANT, listOf("ಪುಸ್ತಕ", "ಪಾಠ")),
            KannadaLetter("c20", "ಫ", "ఫ", "pha", "pha", LetterCategory.CONSONANT, listOf("ಫಲ", "ಫಲಕ")),
            KannadaLetter("c21", "ಬ", "బ", "ba", "ba", LetterCategory.CONSONANT, listOf("ಬಾಳೆ", "ಬಾವಿ")),
            KannadaLetter("c22", "ಭ", "భ", "bha", "bha", LetterCategory.CONSONANT, listOf("ಭೂಮಿ", "ಭಾರತ")),
            KannadaLetter("c23", "ಮ", "మ", "ma", "ma", LetterCategory.CONSONANT, listOf("ಮನೆ", "ಮಾತು")),
            KannadaLetter("c24", "ಯ", "య", "ya", "ya", LetterCategory.CONSONANT, listOf("ಯಾರು", "ಯಾತ್ರೆ")),
            KannadaLetter("c25", "ರ", "ర", "ra", "ra", LetterCategory.CONSONANT, listOf("ರಾಜ", "ರಾತ್ರಿ")),
            KannadaLetter("c26", "ಲ", "ల", "la", "la", LetterCategory.CONSONANT, listOf("ಲೋಕ", "ಲಾಲಿ")),
            KannadaLetter("c27", "ವ", "వ", "va", "va", LetterCategory.CONSONANT, listOf("ವಾಕ್ಯ", "ವಾಯು")),
            KannadaLetter("c28", "ಶ", "శ", "śa", "sha", LetterCategory.CONSONANT, listOf("ಶಕ್ತಿ", "ಶಾಲೆ")),
            KannadaLetter("c29", "ಷ", "ష", "ṣa", "sha", LetterCategory.CONSONANT, listOf("ಷಟ್", "ಷರತ್ತು")),
            KannadaLetter("c30", "ಸ", "స", "sa", "sa", LetterCategory.CONSONANT, listOf("ಸೂರ್ಯ", "ಸಾಕು")),
            KannadaLetter("c31", "ಹ", "హ", "ha", "ha", LetterCategory.CONSONANT, listOf("ಹುಡುಗ", "ಹುಣ್ಣಿಮೆ")),
            KannadaLetter("c32", "ಳ", "ళ", "ḷa", "la", LetterCategory.CONSONANT, listOf("ಳ", "ಳಕು"))
        )
        
        // Sample Kannada words data
        private val kannadaWords = listOf(
            // Numbers (ಸಂಖ್ಯೆಗಳು)
            KannadaWord("n1", "ಒಂದು", "ఒండు", "ondu", "on-du", "One", WordCategory.NUMBERS, Difficulty.BEGINNER),
            KannadaWord("n2", "ಎರಡು", "ఇద్దరు", "eradu", "e-ra-du", "Two", WordCategory.NUMBERS, Difficulty.BEGINNER),
            KannadaWord("n3", "ಮೂರು", "ముగ్దరు", "mooru", "moo-ru", "Three", WordCategory.NUMBERS, Difficulty.BEGINNER),
            KannadaWord("n4", "ನಾಲ್ಕು", "నాలుగు", "naalku", "naal-ku", "Four", WordCategory.NUMBERS, Difficulty.BEGINNER),
            KannadaWord("n5", "ಐದು", "అయిదు", "aidu", "ai-du", "Five", WordCategory.NUMBERS, Difficulty.BEGINNER),
            
            // Colors (ಬಣ್ಣಗಳು)
            KannadaWord("c1", "ಬಿಳಿ", "తెలుపు", "bili", "bi-li", "White", WordCategory.COLORS, Difficulty.BEGINNER),
            KannadaWord("c2", "ಕಪ್ಪು", "నలుపు", "kappu", "kap-pu", "Black", WordCategory.COLORS, Difficulty.BEGINNER),
            KannadaWord("c3", "ಕೆಂಪು", "ఎరుపు", "kempu", "kem-pu", "Red", WordCategory.COLORS, Difficulty.BEGINNER),
            KannadaWord("c4", "ನೀಲಿ", "నీలం", "neeli", "nee-li", "Blue", WordCategory.COLORS, Difficulty.BEGINNER),
            KannadaWord("c5", "ಹಸಿರು", "పచ్చ", "hasiru", "ha-si-ru", "Green", WordCategory.COLORS, Difficulty.BEGINNER),
            
            // Family (ಕುಟುಂಬ)
            KannadaWord("f1", "ತಾಯಿ", "అమ్మ", "taayi", "taa-yi", "Mother", WordCategory.FAMILY, Difficulty.BEGINNER),
            KannadaWord("f2", "ತಂದೆ", "నాన్న", "tande", "tan-de", "Father", WordCategory.FAMILY, Difficulty.BEGINNER),
            KannadaWord("f3", "ಮಗ", "కొడుకు", "maga", "ma-ga", "Son", WordCategory.FAMILY, Difficulty.BEGINNER),
            KannadaWord("f4", "ಮಗಳು", "కుమార్తె", "magalu", "ma-ga-lu", "Daughter", WordCategory.FAMILY, Difficulty.BEGINNER),
            KannadaWord("f5", "ಸಹೋದರ", "సోదరుడు", "sahodara", "sa-ho-da-ra", "Brother", WordCategory.FAMILY, Difficulty.INTERMEDIATE),
            
            // Greetings (ನಮಸ್ಕಾರಗಳು)
            KannadaWord("g1", "ನಮಸ್ಕಾರ", "నమస్కారం", "namaskara", "na-mas-ka-ra", "Hello/Goodbye", WordCategory.GREETINGS, Difficulty.BEGINNER),
            KannadaWord("g2", "ಶುಭೋದಯ", "శుభోదయం", "shubhodaya", "shu-bho-da-ya", "Good Morning", WordCategory.GREETINGS, Difficulty.INTERMEDIATE),
            KannadaWord("g3", "ಶುಭ ರಾತ್ರಿ", "శుభ రాత్రి", "shubha raatri", "shu-bha raa-tri", "Good Night", WordCategory.GREETINGS, Difficulty.INTERMEDIATE),
            KannadaWord("g4", "ಧನ್ಯವಾದ", "ధన్యవాదాలు", "dhanyavaada", "dha-nya-vaa-da", "Thank You", WordCategory.GREETINGS, Difficulty.BEGINNER),
            KannadaWord("g5", "ಕ್ಷಮಿಸಿ", "క్షమించండి", "kshamisi", "ksha-mi-si", "Sorry/Excuse me", WordCategory.GREETINGS, Difficulty.INTERMEDIATE)
        )
        
        // Sample lessons
        private val lessons = listOf(
            Lesson(
                id = "lesson1",
                title = "Kannada Vowels",
                titleKannada = "ಕನ್ನಡ ಸ್ವರಗಳು",
                description = "Learn the basic vowels of Kannada alphabet",
                content = listOf(
                    LessonContent.LetterContent(kannadaLetters[0]),
                    LessonContent.LetterContent(kannadaLetters[1]),
                    LessonContent.LetterContent(kannadaLetters[2])
                ),
                difficulty = Difficulty.BEGINNER,
                estimatedTime = 15
            ),
            Lesson(
                id = "lesson2",
                title = "Basic Numbers",
                titleKannada = "ಮೂಲ ಸಂಖ್ಯೆಗಳು",
                description = "Learn to count from one to five in Kannada",
                content = listOf(
                    LessonContent.WordContent(kannadaWords[0]),
                    LessonContent.WordContent(kannadaWords[1]),
                    LessonContent.WordContent(kannadaWords[2])
                ),
                difficulty = Difficulty.BEGINNER,
                estimatedTime = 20
            )
        )
    }
}
