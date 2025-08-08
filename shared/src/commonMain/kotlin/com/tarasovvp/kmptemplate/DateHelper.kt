package com.tarasovvp.kmptemplate

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


class DateHelper {

    fun getCurrentDate(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val dd = now.date.dayOfMonth.toString().padStart(2, '0')
        val mm = now.date.monthNumber.toString().padStart(2, '0')
        val yy = now.date.year.toString()
        return "$dd-$mm-$yy"
    }
}