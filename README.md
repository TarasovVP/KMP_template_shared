# KMP Template — Shared Module

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.0-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/multiplatform.html)
[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF.svg)](https://kotlinlang.org/docs/multiplatform.html)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A standalone Kotlin Multiplatform library that exposes one shared API to native Android, iOS, Desktop, and Web applications.

This repository is the shared-code example from [**KMP: Integrating into Native Projects**](https://medium.com/@vptarasov/kmp-integrating-into-native-projects-f0f35091706d). Together with four separate platform repositories, it demonstrates how a KMP library can be distributed to applications that retain their native build systems and UI technologies.

## What the module provides

The public `SystemInfo` API combines common Kotlin code with platform-specific implementations:

```kotlin
val systemInfo = SystemInfo()

systemInfo.getInfo()
systemInfo.getDeviceModel()
systemInfo.getOsVersion()
```

- `getInfo()` returns a shared greeting, platform identifier, and local date.
- `getDeviceModel()` returns an appropriate device or runtime description.
- `getOsVersion()` returns the operating-system name and version.

Platform details are implemented through Kotlin `expect` / `actual` declarations while the public contract remains in `commonMain`.

## Targets and consumers

| Target | Distributed as | Native consumer |
|---|---|---|
| Android | Maven artifact `shared-android` | [KMP_template_android](https://github.com/TarasovVP/KMP_template_android) |
| iOS | Static `shared.xcframework` / CocoaPods framework | [KMP_template_ios](https://github.com/TarasovVP/KMP_template_ios) |
| Desktop | Maven artifact `shared-jvm` | [KMP_template_desktop](https://github.com/TarasovVP/KMP_template_desktop) |
| Web | Kotlin/JS ES module with TypeScript declarations | [KMP_template_web](https://github.com/TarasovVP/KMP_template_web) |

The Android and Desktop artifacts use the coordinates:

```text
com.tarasovvp.kmptemplate:shared-<target>:1.0
```

## Companion repositories

These repositories were created as independent examples for the same article:

| Repository | Role |
|---|---|
| **KMP_template_shared** | KMP library and the source of the shared contract |
| [KMP_template_android](https://github.com/TarasovVP/KMP_template_android) | Native Android app with Jetpack Compose |
| [KMP_template_ios](https://github.com/TarasovVP/KMP_template_ios) | Native iOS app with SwiftUI |
| [KMP_template_desktop](https://github.com/TarasovVP/KMP_template_desktop) | Native JVM desktop app with JavaFX |
| [KMP_template_web](https://github.com/TarasovVP/KMP_template_web) | Native JavaScript web app with Vite |

## Project structure

```text
shared/src/
├── commonMain/     # Public API and shared date logic
├── androidMain/    # Android device and OS implementation
├── iosMain/        # iOS device and OS implementation
├── jvmMain/        # Desktop JVM implementation
└── jsMain/         # Browser / Node implementation and JS exports
```

## Requirements

- JDK 17+
- Gradle 8.13 (wrapper included)
- Android SDK 36 for the Android target
- Xcode on macOS for Apple targets

The library uses Kotlin 2.2.0, Android Gradle Plugin 8.11.1, and `kotlinx-datetime` 0.7.1.

## Build

Build all available targets:

```shell
./gradlew :shared:assemble
```

Publish Android and JVM artifacts to the local Maven repository:

```shell
./gradlew :shared:publishToMavenLocal
```

Create the release XCFramework used by the iOS application:

```shell
./gradlew :shared:assembleSharedReleaseXCFramework
```

Create the production Kotlin/JS library consumed by the web application:

```shell
./gradlew :shared:jsBrowserProductionLibraryDistribution
```

## Using the generated artifacts

- **Android and Desktop:** publish to Maven Local before building the consumer.
- **iOS:** copy the generated `shared.xcframework` into the local Swift package used by the iOS repository.
- **Web:** install the generated production library as the local package declared in the web repository.

For the complete integration walkthrough, see [KMP: Integrating into Native Projects](https://medium.com/@vptarasov/kmp-integrating-into-native-projects-f0f35091706d).

## Later automation

[KMP_template_agents](https://github.com/TarasovVP/KMP_template_agents) is a separate project created later to automate coordinated changes across this existing five-repository example. It clones these repositories, propagates shared artifacts, delegates platform work to specialized AI agents, and verifies the result. The automation harness is optional and is not required to use or build this library.

The design of that harness is described in [AI Agents for a KMP Developer](https://levelup.gitconnected.com/ai-agents-for-a-kmp-developer-82af4e8851a9).

## License

Licensed under the [MIT License](LICENSE).
