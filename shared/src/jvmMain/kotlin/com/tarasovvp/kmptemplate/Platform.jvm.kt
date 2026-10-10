package com.tarasovvp.kmptemplate

class JvmPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "JVM ${System.getProperty("java.version")}"
}

actual fun getPlatform(): PlatformHelper = JvmPlatformHelper()

// The JVM has no notion of a device model; report a generic desktop label with OS and arch.
internal actual fun platformDeviceModel(): String =
    "Desktop (${System.getProperty("os.name") ?: "unknown OS"} ${System.getProperty("os.arch") ?: "unknown arch"})"