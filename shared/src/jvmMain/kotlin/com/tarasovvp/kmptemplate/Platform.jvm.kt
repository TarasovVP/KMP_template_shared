package com.tarasovvp.kmptemplate

class JvmPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "JVM ${System.getProperty("java.version")}"
}

actual fun getPlatform(): PlatformHelper = JvmPlatformHelper()