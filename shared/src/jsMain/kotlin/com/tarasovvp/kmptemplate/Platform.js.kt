package com.tarasovvp.kmptemplate

class JsPlatformHelper : PlatformHelper {

    override val name: String = "JS ${js("navigator.userAgent") as? String ?: "Node"}"
}

actual fun getPlatform(): PlatformHelper = JsPlatformHelper()