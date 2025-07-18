package com.github.greysteklo.anotherone.calculator.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "calculation_history")
data class CalculationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val expression: String,
    val result: String,
    val date: LocalDate = LocalDate.now(),
)
