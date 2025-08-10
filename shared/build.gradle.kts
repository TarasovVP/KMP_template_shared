plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.2.0"
    id("com.android.library") version "8.11.1"
    id("maven-publish")
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
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        }
        androidMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
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

publishing {
    publications {
        create<MavenPublication>("aar") {
            groupId = "com.tarasovv.kmptemplate"
            artifactId = "shared"
            version = "1.0"
            artifact("$buildDir/outputs/aar/shared-release.aar")
            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                project.configurations.getByName("api").allDependencies.forEach { dependency ->
                    if (dependency is ExternalModuleDependency) {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dependency.group)
                        dependencyNode.appendNode("artifactId", dependency.name)
                        dependencyNode.appendNode("version", dependency.version)
                    }
                }
            }
        }
    }
}
