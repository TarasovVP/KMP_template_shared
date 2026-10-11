package com.tarasovvp.kmptemplate

class JvmPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "JVM ${System.getProperty("java.version")}"
}

actual fun getPlatform(): PlatformHelper = JvmPlatformHelper()

// The JVM has no notion of a device model; report a generic desktop label with OS and arch.
internal actual fun platformDeviceModel(): String =
    "Desktop (${System.getProperty("os.name") ?: "unknown OS"} ${System.getProperty("os.arch") ?: "unknown arch"})"

// Host OS from the standard os.name / os.version system properties (e.g. "Mac OS X 15.0").
// getProperty can throw SecurityException under a security manager, hence runCatching.
internal actual fun platformOsVersion(): String {
    fun prop(key: String): String =
        runCatching { System.getProperty(key) }.getOrNull().orEmpty().trim().ifBlank { "unknown" }
    return "${prop("os.name")} ${prop("os.version")}"
}