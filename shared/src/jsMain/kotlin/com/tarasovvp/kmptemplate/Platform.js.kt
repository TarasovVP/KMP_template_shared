package com.tarasovvp.kmptemplate

class JsPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "JS ${js("navigator.userAgent") as? String ?: "Node"}"
}

actual fun getPlatform(): PlatformHelper = JsPlatformHelper()