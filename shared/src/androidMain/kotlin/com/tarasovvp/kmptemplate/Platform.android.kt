package com.tarasovvp.kmptemplate

import android.os.Build


class AndroidPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): PlatformHelper = AndroidPlatformHelper()

internal actual fun platformDeviceModel(): String {
    val manufacturer = Build.MANUFACTURER.orEmpty()
    val model = Build.MODEL.orEmpty()
    return when {
        model.isBlank() -> manufacturer.ifBlank { "Unknown" }
        manufacturer.isBlank() || model.startsWith(manufacturer, ignoreCase = true) -> model
        else -> "${manufacturer.replaceFirstChar { it.uppercaseChar() }} $model"
    }
}