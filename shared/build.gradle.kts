import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.2.0"
    id("com.android.library") version "8.11.1"
    id("org.jetbrains.kotlin.native.cocoapods")
    id("maven-publish")
}

group = "com.tarasovvp.kmptemplate"
version = "1.0"

kotlin {
    // Android
    androidTarget {
        publishLibraryVariants("release")
    }
    // iOS
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            xcf.add(this)
        }
    }
    cocoapods {
        summary = "Shared KMP module"
        homepage = "https://github.com/TarasovVP/KMP_template_shared"
        framework {
            baseName = "shared"
        }
    }
    // Desktop
    jvm()
    // Web
    js(IR) {
        browser()
        // Executable bundle (dev/prod)
        binaries.executable()
        // Library for npm (ESM)
        binaries.library()
        compilerOptions {
            target = "es2015"
            generateTypeScriptDefinitions()
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        }
    }
}

android {
    namespace = "com.tarasovvp.kmptemplate"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}
