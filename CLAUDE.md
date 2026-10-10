# KMP_template_shared

The `:shared` Kotlin Multiplatform module (Kotlin 2.2.0, Gradle 8.13). Targets: android, ios (iosX64/iosArm64/iosSimulatorArm64), jvm, js.
The public API lives in `shared/src/commonMain`; platform parts are `actual` declarations in `androidMain`, `iosMain`, `jvmMain`, `jsMain`.

## Build
- everything: `./gradlew assemble`
- iOS XCFramework: `./gradlew assembleSharedReleaseXCFramework`
- JS library: `./gradlew :shared:jsBrowserProductionLibraryDistribution`
- Maven artifacts for android/desktop (`shared-android:1.0`, `shared-jvm:1.0`): `./gradlew :shared:publishToMavenLocal`

## Where shared goes
The consumer apps (separate repositories next to this one) take it from here:
android and desktop from `~/.m2`, iOS from a prebuilt XCFramework, web from the JS library directory.
In the `kmp-agents` harness all of this is done by `scripts/propagate.sh`.
