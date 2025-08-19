plugins {
    kotlin("multiplatform") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    id("org.jetbrains.compose") version "1.5.10"
}

group = "com.kannadapwa"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "KannadaPWA.js"
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.html.svg)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
    }
}

tasks.register("buildWeb") {
    dependsOn("jsBrowserDevelopmentRun")
}

tasks.register("buildProduction") {
    dependsOn("jsBrowserProductionWebpack")
}
