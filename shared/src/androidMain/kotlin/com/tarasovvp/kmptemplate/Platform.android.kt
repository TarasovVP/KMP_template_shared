package com.tarasovvp.kmptemplate

import android.os.Build


class AndroidPlatformHelper : PlatformHelper {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): PlatformHelper = AndroidPlatformHelper()