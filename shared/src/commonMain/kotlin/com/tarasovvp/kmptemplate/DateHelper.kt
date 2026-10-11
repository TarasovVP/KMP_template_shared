package com.tarasovvp.kmptemplate

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DateHelper {
    // kotlin.time.Clock/Instant are @ExperimentalTime in Kotlin 2.2; the opt-in is
    // scoped to this function so the public API carries no opt-in requirement.
    @OptIn(ExperimentalTime::class)
    fun getCurrentDate(): String {
        val now = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val formatter = LocalDate.Format {
            day(padding = Padding.ZERO)
            char('-')
            monthNumber(padding = Padding.ZERO)
            char('-')
            year()
        }
        return now.date.format(formatter)
    }
}