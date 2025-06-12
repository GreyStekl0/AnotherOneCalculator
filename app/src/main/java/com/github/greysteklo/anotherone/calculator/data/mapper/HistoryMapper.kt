package com.github.greysteklo.anotherone.calculator.data.mapper

import com.github.greysteklo.anotherone.calculator.data.local.entity.CalculationEntity
import com.github.greysteklo.anotherone.calculator.domain.model.Calculation

fun Calculation.toEntity(): CalculationEntity = CalculationEntity(expression = this.expression, result = this.result)

fun CalculationEntity.toDomain(): Calculation = Calculation(expression = this.expression, result = this.result)
