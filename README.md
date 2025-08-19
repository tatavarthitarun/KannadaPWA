# Kannada PWA - Kotlin Multiplatform

A Progressive Web App (PWA) for learning Kannada language, specifically designed for Telugu speakers. Built with Kotlin Multiplatform + Compose Multiplatform, hosted on Firebase.

## 🎯 Project Overview

This PWA helps Telugu speakers learn Kannada by providing:
- Kannada alphabet and vocabulary
- Telugu transliterations for pronunciation
- English transliterations for international users
- Interactive learning exercises
- Progress tracking
- Offline capability

## 🚀 Technology Stack

- **Language**: Kotlin Multiplatform
- **UI Framework**: Compose Multiplatform
- **Build System**: Gradle
- **Target Platform**: Web (JavaScript)
- **Hosting**: Firebase
- **IDE**: Cursor IDE (macOS)

## 📁 Project Structure

```
KannadaPWA/
├── build.gradle.kts          # Main build configuration
├── settings.gradle.kts       # Project settings
├── gradle.properties         # Gradle properties
├── src/
│   └── jsMain/
│       └── kotlin/
│           └── Main.kt       # Main application entry point
├── web/
│   ├── index.html            # Main HTML file
│   ├── manifest.json         # PWA manifest
│   └── service-worker.js     # Service worker for offline
└── gradle/
    └── wrapper/              # Gradle wrapper
```

## 🛠️ Development Setup

### Prerequisites
- macOS with Cursor IDE
- Java 11 or higher
- Git

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd KannadaPWA
   ```

2. **Open in Cursor IDE**
   - Open Cursor IDE
   - File → Open Folder → Select KannadaPWA folder

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run development server**
   ```bash
   ./gradlew jsBrowserDevelopmentRun
   ```

5. **Build for production**
   ```bash
   ./gradlew jsBrowserProductionWebpack
   ```

## 🎮 Development Commands

| Command | Description |
|---------|-------------|
| `./gradlew build` | Build the entire project |
| `./gradlew jsBrowserDevelopmentRun` | Start development server |
| `./gradlew jsBrowserProductionWebpack` | Build production bundle |
| `./gradlew clean` | Clean build artifacts |

## 🌐 PWA Features

- **Offline Capability**: Service worker caches essential content
- **Installable**: Can be installed on devices like a native app
- **Responsive Design**: Works on all device sizes
- **Fast Loading**: Optimized for performance

## 📱 Browser Support

- Chrome (recommended)
- Firefox
- Safari
- Edge

## 🔧 Configuration

### Gradle Properties
- `kotlin.mpp.enableGranularSourceSetsMetadata=true`
- `compose.web.experimental.uikit.enabled=true`
- `compose.web.experimental.foundation.enabled=true`

### Build Configuration
- Kotlin version: 1.9.20
- Compose version: 1.5.10
- Target: JavaScript (IR)

## 📚 Learning Content Structure

### Alphabet (ವರ್ಣಮಾಲೆ)
- Vowels (ಸ್ವರಗಳು)
- Consonants (ವ್ಯಂಜನಗಳು)
- Conjuncts (ಸಂಯುಕ್ತಾಕ್ಷರಗಳು)

### Vocabulary
- Numbers (ಸಂಖ್ಯೆಗಳು)
- Colors (ಬಣ್ಣಗಳು)
- Family (ಕುಟುಂಬ)
- Common objects (ಸಾಮಾನ್ಯ ವಸ್ತುಗಳು)

### Phrases
- Greetings (ನಮಸ್ಕಾರಗಳು)
- Basic conversations (ಮೂಲ ಸಂಭಾಷಣೆಗಳು)
- Questions (ಪ್ರಶ್ನೆಗಳು)

## 🚀 Deployment

### Firebase Hosting
1. Install Firebase CLI
2. Initialize Firebase project
3. Build production bundle
4. Deploy to Firebase

```bash
npm install -g firebase-tools
firebase login
firebase init hosting
./gradlew jsBrowserProductionWebpack
firebase deploy
```

## 🤝 Contributing

This is a personal project for learning purposes. Feel free to fork and modify for your own use.

## 📄 License

Personal use only. Not for commercial distribution.

## 🆘 Troubleshooting

### Common Issues

1. **Gradle sync fails**
   - Check Java version (11+ required)
   - Clear Gradle cache: `./gradlew clean`

2. **Build fails**
   - Ensure all dependencies are resolved
   - Check Kotlin and Compose versions compatibility

3. **PWA not working**
   - Verify service worker is registered
   - Check manifest.json configuration
   - Test in Chrome DevTools

## 📞 Support

For personal use and learning purposes. Built with ❤️ using Kotlin Multiplatform.
