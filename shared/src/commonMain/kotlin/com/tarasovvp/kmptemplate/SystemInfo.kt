package com.tarasovvp.kmptemplate

class SystemInfo {
    private val platformHelper: PlatformHelper = getPlatform()
    private val dateHelper: DateHelper = DateHelper()

    fun getInfo(): String {
        return "Hello!\n" +
                "App opens in ${platformHelper.name}\n" +
                "Current date: ${dateHelper.getCurrentDate()}"
    }
}