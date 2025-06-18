package com.github.greysteklo.anotherone.calculator.domain.model

import java.time.LocalDate

data class SavedCalculation(
    val expression: String,
    val result: String,
    val date: LocalDate,
)
