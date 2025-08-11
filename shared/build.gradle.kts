plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.2.0"
    id("com.android.library") version "8.11.1"
    id("maven-publish")
}

group = "com.tarasovvp.kmptemplate"
version = "1.8"

kotlin {
    // Android
    androidTarget{
        publishLibraryVariants("release")
    }
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
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        when (name) {
            "jvm" -> artifactId = "shared-jvm"
            "androidRelease" -> artifactId = "shared-android"
        }
    }
    repositories {
        mavenLocal()
    }
}
