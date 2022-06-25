package com.devamsba.managebudget.common.domain.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

class ZonedDateTimeTypeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toZonedDateTime(value: Long): ZonedDateTime {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toString(value: ZonedDateTime): Long {
        return value.toInstant().toEpochMilli()
    }
}