package com.tarasovvp.kmptemplate

class JsPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "JS ${js("navigator.userAgent") as? String ?: "Node"}"
}

actual fun getPlatform(): PlatformHelper = JsPlatformHelper()

// Browsers do not expose a hardware model (navigator.platform is deprecated); report the runtime kind.
internal actual fun platformDeviceModel(): String =
    if (js("typeof navigator !== 'undefined'") as Boolean) "Browser" else "Node"