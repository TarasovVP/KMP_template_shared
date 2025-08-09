package com.tarasovvp.kmptemplate

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

class DateHelper {
    fun getCurrentDate(): String {
        val now = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val formatter = LocalDate.Format {
            dayOfMonth(padding = Padding.ZERO)
            char('-')
            monthNumber(padding = Padding.ZERO)
            char('-')
            year()
        }
        return now.date.format(formatter)
    }
}