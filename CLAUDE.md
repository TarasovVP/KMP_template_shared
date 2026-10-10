# KMP_template_shared

The `:shared` module (Kotlin 2.2.0, AGP 8.11.1, Gradle 8.13). Targets: android (release), iosX64/iosArm64/iosSimulatorArm64 (static framework `shared`), jvm, js (IR, `binaries.library()` only).
Edit only inside this directory. Publishing (`publish*`), propagate and commits are done by the orchestrator.

## Check every new public API against the four exports
1. **JS.** Classes from commonMain are not visible outside. A top-level facade with `@JsExport` in `jsMain` is needed, like `getInfo()` in `SystemInfoFacade.kt`.
   If shared gains a new JS dependency, it must be declared in `KMP_template_web/package.json`, otherwise `propagate.sh` will fail - mention this in the report.
2. **Swift.** Check how the name and `suspend` look in the ObjC header (`shared/build/XCFrameworks/release/shared.xcframework/ios-arm64/shared.framework/Headers/shared.h` after a build): `swift_name`, `suspend` becomes a completionHandler/async, default values, sealed hierarchies and generics are lost or changed.
3. **Java.** Desktop calls `shared-jvm` from Java: default parameters need `@JvmOverloads`, top-level functions end up in a `<File>Kt` class (use `@file:JvmName` if needed).
4. **Android.** Code in `androidMain`/commonMain, `minSdk` 24, `compileSdk` 36; the consumer uses `shared-android`.
In the report, mark each of the four exports as verified or not, and by what.

## Build (from ~/kmp-agents, always with -p)
- everything: `./KMP_template_shared/gradlew -p KMP_template_shared assemble`
- XCFramework: `... assembleSharedReleaseXCFramework`; JS library: `... :shared:jsBrowserProductionLibraryDistribution`
There are no tests in the module yet: verification is a successful `assemble` plus inspecting the header/`.d.ts`.

Canary: SHARED-C6MPYQ
