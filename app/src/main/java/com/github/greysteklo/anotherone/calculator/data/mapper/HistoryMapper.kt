package com.github.greysteklo.anotherone.calculator.data.mapper

import com.github.greysteklo.anotherone.calculator.data.local.entity.CalculationEntity
import com.github.greysteklo.anotherone.calculator.domain.model.SavedCalculation

fun SavedCalculation.toEntity(): CalculationEntity = CalculationEntity(expression = this.expression, result = this.result)

fun CalculationEntity.toDomain(): SavedCalculation = SavedCalculation(expression = this.expression, result = this.result, date = this.date)
