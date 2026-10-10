# KMP_template_shared

Модуль `:shared` (Kotlin 2.2.0, AGP 8.11.1, Gradle 8.13). Таргеты: android (release), iosX64/iosArm64/iosSimulatorArm64 (статический фреймворк `shared`), jvm, js (IR, только `binaries.library()`).
Правки делай только внутри этого каталога. Публикацию (`publish*`), propagate и коммиты делает оркестратор.

## Каждый новый публичный API проверяй по четырём экспортам
1. **JS.** Классы из commonMain наружу не видны. Нужен top-level фасад с `@JsExport` в `jsMain`, как `getInfo()` в `SystemInfoFacade.kt`.
   Если shared получает новую JS-зависимость, её нужно объявить в `KMP_template_web/package.json`, иначе `propagate.sh` упадёт — укажи это в отчёте.
2. **Swift.** Смотри, как имя и `suspend` выглядят в ObjC-заголовке (`shared/build/XCFrameworks/release/shared.xcframework/ios-arm64/shared.framework/Headers/shared.h` после сборки): `swift_name`, `suspend` превращается в completionHandler/async, значения по умолчанию, sealed-иерархии и generics теряются или меняются.
3. **Java.** Desktop вызывает `shared-jvm` из Java: параметры по умолчанию требуют `@JvmOverloads`, top-level функции окажутся в классе `<Файл>Kt` (при необходимости `@file:JvmName`).
4. **Android.** Код в `androidMain`/commonMain, `minSdk` 24, `compileSdk` 36; потребитель берёт `shared-android`.
В отчёте отметь по каждому из четырёх экспортов: проверено или нет и чем.

## Сборка (из ~/kmp-agents, всегда через -p)
- всё: `./KMP_template_shared/gradlew -p KMP_template_shared assemble`
- XCFramework: `... assembleSharedReleaseXCFramework`; JS-библиотека: `... :shared:jsBrowserProductionLibraryDistribution`
Тестов в модуле пока нет: проверка — успешный `assemble` и осмотр заголовка/`.d.ts`.

Canary: SHARED-C6MPYQ
