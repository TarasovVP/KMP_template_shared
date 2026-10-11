package com.tarasovvp.kmptemplate

class SystemInfo {
    private val platformHelper: PlatformHelper = getPlatform()
    private val dateHelper: DateHelper = DateHelper()

    fun getInfo(): String {
        return """
            Hello from KMP module!
            Platform: ${platformHelper.platformIdentifier}
            Date: ${dateHelper.getCurrentDate()}
        """.trimIndent()
    }

    /**
     * Device model of the current platform, e.g. "Google Pixel 8" (Android),
     * "iPhone15,2" (iOS hardware identifier), "Desktop (Mac OS X aarch64)" (JVM),
     * "Browser" / "Node" (JS). Not included in [getInfo] to keep its output unchanged.
     */
    fun getDeviceModel(): String = platformDeviceModel()

    /**
     * Operating system name and version of the current platform, e.g. "Android 14 (API 34)" (Android),
     * "iOS 17.5" (iOS), "Mac OS X 15.0" (JVM), "macOS 10.15.7" / "Windows 10.0" (JS in a browser,
     * parsed from the user agent), "darwin (Node v22.1.0)" (JS in Node).
     * Always a non-empty string; never throws (falls back to "unknown" parts). Not included in [getInfo].
     */
    fun getOsVersion(): String = platformOsVersion()
}
