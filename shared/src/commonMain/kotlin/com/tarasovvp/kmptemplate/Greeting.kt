package com.tarasovvp.kmptemplate

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val formattedDateTime = formattedDateTime()
        return "Hello, ${platform.name}! Now is $formattedDateTime"
    }

    @OptIn(ExperimentalTime::class)
    fun formattedDateTime(): String {
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

        return currentDateTime.format(formatter)
    }
}