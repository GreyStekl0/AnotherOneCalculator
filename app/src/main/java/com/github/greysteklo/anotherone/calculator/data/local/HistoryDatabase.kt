package com.github.greysteklo.anotherone.calculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.greysteklo.anotherone.calculator.data.local.dao.CalculationDao
import com.github.greysteklo.anotherone.calculator.data.local.entity.CalculationEntity

@Database(
    version = 1,
    entities = [CalculationEntity::class],
)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun calculationDao(): CalculationDao
}
