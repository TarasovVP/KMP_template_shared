plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.2.0"
    id("com.android.library") version "8.11.1"
}

kotlin {
    // Android
    androidTarget()
    // iOS
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    // Desktop
    jvm()
    // Web
    js(IR) {
        browser {
            binaries.executable()
        }
    }

    sourceSets {

    }
}

android {
    namespace = "com.kmp.template"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}
