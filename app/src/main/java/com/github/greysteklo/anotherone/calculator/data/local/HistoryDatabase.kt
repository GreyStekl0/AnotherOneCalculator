package com.github.greysteklo.anotherone.calculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.greysteklo.anotherone.calculator.data.local.converter.DateConverters
import com.github.greysteklo.anotherone.calculator.data.local.dao.CalculationDao
import com.github.greysteklo.anotherone.calculator.data.local.entity.CalculationEntity

@Database(
    version = 2,
    entities = [CalculationEntity::class],
)
@TypeConverters(DateConverters::class)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun calculationDao(): CalculationDao
}
