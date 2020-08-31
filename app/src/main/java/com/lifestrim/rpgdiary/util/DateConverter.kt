package com.lifestrim.rpgdiary.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    fun getDayMonthHoursMinute(date: Date): String? {
        return SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date).toString()
    }
}