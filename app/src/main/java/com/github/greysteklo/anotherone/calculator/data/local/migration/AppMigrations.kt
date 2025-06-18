package com.github.greysteklo.anotherone.calculator.data.local.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 =
    object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE calculation_history ADD COLUMN date INTEGER NOT NULL DEFAULT 0")
        }
    }

val ALL_MIGRATIONS = arrayOf(MIGRATION_1_2)
