package com.tarasovvp.kmptemplate

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

class Greeting {
    private val platform: Platform = getPlatform()

    @OptIn(ExperimentalTime::class)
    fun greet(): String {
        val currentDateTime = kotlin.time.Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val formatter = LocalDateTime.Format {
            day()
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
        return "Hello, ${platform.name}! Now is $formattedDateTime"
    }
}