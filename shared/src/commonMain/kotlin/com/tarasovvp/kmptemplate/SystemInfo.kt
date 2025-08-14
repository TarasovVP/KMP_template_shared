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
}
