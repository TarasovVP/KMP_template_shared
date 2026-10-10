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
}
