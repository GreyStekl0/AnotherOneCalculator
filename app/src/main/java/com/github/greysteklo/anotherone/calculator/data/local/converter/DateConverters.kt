package com.github.greysteklo.anotherone.calculator.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate

object DateConverters {
    @TypeConverter
    fun fromEpochDay(value: Long?): LocalDate? = value?.let { LocalDate.ofEpochDay(it) }

    @TypeConverter
    fun dateToEpochDay(date: LocalDate?): Long? = date?.toEpochDay()
}
