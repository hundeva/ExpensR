package com.hdeva.expensr.domain.converter

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
    }

    @TypeConverter
    fun toTimestamp(calendar: Calendar): Long {
        return calendar.timeInMillis
    }
}
