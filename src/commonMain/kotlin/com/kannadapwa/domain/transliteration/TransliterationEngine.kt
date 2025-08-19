package com.kannadapwa.domain.transliteration

import com.kannadapwa.data.models.KannadaLetter
import com.kannadapwa.data.models.LetterCategory

/**
 * Engine for transliterating Kannada text to Telugu and English
 */
class TransliterationEngine {
    
    /**
     * Transliterates Kannada text to Telugu script
     */
    fun kannadaToTelugu(kannadaText: String): String {
        return kannadaText.map { char ->
            kannadaToTeluguMap[char] ?: char
        }.joinToString("")
    }
    
    /**
     * Transliterates Kannada text to English romanization
     */
    fun kannadaToEnglish(kannadaText: String): String {
        return kannadaText.map { char ->
            kannadaToEnglishMap[char] ?: char
        }.joinToString("")
    }
    
    /**
     * Gets pronunciation guide for a Kannada letter
     */
    fun getPronunciation(letter: Char): String {
        return pronunciationMap[letter] ?: letter.toString()
    }
    
    companion object {
        // Kannada to Telugu character mapping
        private val kannadaToTeluguMap: Map<Char, Char> = mapOf(
            // Vowels (ಸ್ವರಗಳು)
            'ಅ' to 'అ', 'ಆ' to 'ఆ', 'ಇ' to 'ఇ', 'ಈ' to 'ఈ',
            'ಉ' to 'ఉ', 'ಊ' to 'ఊ', 'ಋ' to 'ఋ', 'ೠ' to 'ౠ',
            'ಎ' to 'ఎ', 'ಏ' to 'ఏ', 'ಐ' to 'ఐ', 'ಒ' to 'ఒ',
            'ಓ' to 'ఓ', 'ಔ' to 'ఔ',
            
            // Consonants (ವ್ಯಂಜನಗಳು)
            'ಕ' to 'క', 'ಖ' to 'ఖ', 'ಗ' to 'గ', 'ಘ' to 'ఘ', 'ಙ' to 'ఙ',
            'ಚ' to 'చ', 'ಛ' to 'ఛ', 'ಜ' to 'జ', 'ಝ' to 'ఝ', 'ಞ' to 'ఞ',
            'ಟ' to 'ట', 'ಠ' to 'ఠ', 'ಡ' to 'డ', 'ಢ' to 'ఢ', 'ಣ' to 'ణ',
            'ತ' to 'త', 'ಥ' to 'థ', 'ದ' to 'ద', 'ಧ' to 'ధ', 'ನ' to 'న',
            'ಪ' to 'ప', 'ಫ' to 'ఫ', 'ಬ' to 'బ', 'ಭ' to 'భ', 'ಮ' to 'మ',
            'ಯ' to 'య', 'ರ' to 'ర', 'ಲ' to 'ల', 'ವ' to 'వ',
            'ಶ' to 'శ', 'ಷ' to 'ష', 'ಸ' to 'స', 'ಹ' to 'హ', 'ಳ' to 'ళ'
        )
        
        // Kannada to English romanization mapping
        private val kannadaToEnglishMap: Map<Char, String> = mapOf(
            // Vowels
            'ಅ' to "a", 'ಆ' to "ā", 'ಇ' to "i", 'ಈ' to "ī",
            'ಉ' to "u", 'ಊ' to "ū", 'ಋ' to "ṛ", 'ೠ' to "ṝ",
            'ಎ' to "e", 'ಏ' to "ē", 'ಐ' to "ai", 'ಒ' to "o",
            'ಓ' to "ō", 'ಔ' to "au",
            
            // Consonants
            'ಕ' to "ka", 'ಖ' to "kha", 'ಗ' to "ga", 'ಘ' to "gha", 'ಙ' to "ṅa",
            'ಚ' to "ca", 'ಛ' to "cha", 'ಜ' to "ja", 'ಝ' to "jha", 'ಞ' to "ña",
            'ಟ' to "ṭa", 'ಠ' to "ṭha", 'ಡ' to "ḍa", 'ಢ' to "ḍha", 'ಣ' to "ṇa",
            'ತ' to "ta", 'ಥ' to "tha", 'ದ' to "da", 'ಧ' to "dha", 'ನ' to "na",
            'ಪ' to "pa", 'ಫ' to "pha", 'ಬ' to "ba", 'ಭ' to "bha", 'ಮ' to "ma",
            'ಯ' to "ya", 'ರ' to "ra", 'ಲ' to "la", 'ವ' to "va",
            'ಶ' to "śa", 'ಷ' to "ṣa", 'ಸ' to "sa", 'ಹ' to "ha", 'ಳ' to "ḷa"
        )
        
        // Pronunciation guide
        private val pronunciationMap: Map<Char, String> = mapOf(
            'ಅ' to "ah", 'ಆ' to "aa", 'ಇ' to "ee", 'ಈ' to "ee",
            'ಉ' to "oo", 'ಊ' to "oo", 'ಋ' to "ri", 'ೠ' to "ree",
            'ಎ' to "e", 'ಏ' to "ay", 'ಐ' to "ai", 'ಒ' to "o",
            'ಓ' to "o", 'ಔ' to "au",
            
            'ಕ' to "ka", 'ಖ' to "kha", 'ಗ' to "ga", 'ಘ' to "gha", 'ಙ' to "nga",
            'ಚ' to "cha", 'ಛ' to "chha", 'ಜ' to "ja", 'ಝ' to "jha", 'ಞ' to "nya",
            'ಟ' to "ta", 'ಠ' to "tha", 'ಡ' to "da", 'ಢ' to "dha", 'ಣ' to "na",
            'ತ' to "ta", 'ಥ' to "tha", 'ದ' to "da", 'ಧ' to "dha", 'ನ' to "na",
            'ಪ' to "pa", 'ಫ' to "pha", 'ಬ' to "ba", 'ಭ' to "bha", 'ಮ' to "ma",
            'ಯ' to "ya", 'ರ' to "ra", 'ಲ' to "la", 'ವ' to "va",
            'ಶ' to "sha", 'ಷ' to "sha", 'ಸ' to "sa", 'ಹ' to "ha", 'ಳ' to "la"
        )
    }
}

/**
 * Extension function to get transliterations for Kannada text
 */
fun String.toTelugu(): String = TransliterationEngine().kannadaToTelugu(this)
fun String.toEnglish(): String = TransliterationEngine().kannadaToEnglish(this)
fun Char.getPronunciation(): String = TransliterationEngine().getPronunciation(this)
