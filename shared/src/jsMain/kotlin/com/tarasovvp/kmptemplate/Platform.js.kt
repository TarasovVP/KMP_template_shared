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

/**
 * Node: `process.platform` (e.g. "darwin", "linux", "win32") plus the Node version, read via `typeof` guards.
 * The `os` module is deliberately not used: requiring it would break browser bundlers (no new JS deps).
 */
private fun nodeOsVersion(): String {
    val platform = js("(typeof process !== 'undefined' && process !== null && typeof process.platform === 'string') ? process.platform : null")
        .unsafeCast<String?>()
    val version = js("(typeof process !== 'undefined' && process !== null && typeof process.version === 'string') ? process.version : null")
        .unsafeCast<String?>()
    val os = platform?.ifBlank { null } ?: "unknown"
    return if (version.isNullOrBlank()) "$os (Node)" else "$os (Node $version)"
}

/**
 * Browser: the OS token parsed from the user agent. Order matters: iOS/Android user agents also contain
 * "Mac OS X"/"Linux". Frozen/reduced user agents may report a fixed version (e.g. "macOS 10.15.7").
 */
private fun browserOsVersion(userAgent: String): String {
    fun find(pattern: String): String? = Regex(pattern).find(userAgent)?.groupValues?.getOrNull(1)
    fun dotted(raw: String) = raw.replace('_', '.')
    find("""(?:iPhone|CPU) OS (\d+(?:[_.]\d+)*)""")?.let { return "iOS ${dotted(it)}" }
    find("""Android (\d+(?:\.\d+)*)""")?.let { return "Android $it" }
    find("""Windows NT (\d+(?:\.\d+)*)""")?.let { return "Windows $it" }
    find("""Mac OS X (\d+(?:[_.]\d+)*)""")?.let { return "macOS ${dotted(it)}" }
    if (userAgent.contains("CrOS")) return "ChromeOS"
    if (userAgent.contains("Linux")) return "Linux"
    return "Browser (unknown OS)"
}

internal actual fun platformOsVersion(): String = runCatching {
    val userAgent = jsUserAgent()
    if (userAgent == null || isNodeRuntime(userAgent)) nodeOsVersion() else browserOsVersion(userAgent)
}.getOrNull()?.ifBlank { null } ?: "unknown"
