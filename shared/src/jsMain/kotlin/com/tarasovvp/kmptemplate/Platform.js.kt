package com.tarasovvp.kmptemplate

/**
 * Returns `navigator.userAgent` when a `navigator` global with a string `userAgent` exists, otherwise null.
 * Uses `typeof` so that an undeclared `navigator` (plain Node < 21) does not throw a ReferenceError.
 */
private fun jsUserAgent(): String? =
    js("(typeof navigator !== 'undefined' && navigator !== null && typeof navigator.userAgent === 'string') ? navigator.userAgent : null")
        .unsafeCast<String?>()

/**
 * Node is reported when there is no usable `navigator` (Node < 21) or when the user agent is Node's own
 * (Node >= 21 exposes `navigator.userAgent` as "Node.js/<major>"). Any other user agent is treated as a browser.
 */
private fun isNodeRuntime(userAgent: String?): Boolean =
    userAgent == null || userAgent.startsWith("Node.js/")

class JsPlatformHelper : PlatformHelper {
    override val platformIdentifier: String = run {
        val userAgent = jsUserAgent()
        if (userAgent == null || isNodeRuntime(userAgent)) "JS Node" else "JS $userAgent"
    }
}

actual fun getPlatform(): PlatformHelper = JsPlatformHelper()

// Browsers do not expose a hardware model (navigator.platform is deprecated); report the runtime kind.
internal actual fun platformDeviceModel(): String =
    if (isNodeRuntime(jsUserAgent())) "Node" else "Browser"
