package com.tarasovvp.kmptemplate

import kotlinx.datetime.*
import kotlinx.datetime.format.*

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val currentDateTime = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val formatter = LocalDateTime.Format {
            dayOfMonth()
            chars("-")
            monthNumber()
            chars("-")
            year()
            chars(" ")
            hour()
            chars(":")
            minute()
        }

        val formattedDateTime = currentDateTime.format(formatter)
        return "Hello, ${platform.name}! Now is $formattedDate"
    }
}