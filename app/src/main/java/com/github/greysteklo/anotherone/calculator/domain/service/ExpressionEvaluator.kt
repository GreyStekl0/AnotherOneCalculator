package com.github.greysteklo.anotherone.calculator.domain.service

import java.math.BigDecimal

interface ExpressionEvaluator {
    fun evaluate(expression: String): Result<BigDecimal>

    fun formatResult(result: BigDecimal): String
}
