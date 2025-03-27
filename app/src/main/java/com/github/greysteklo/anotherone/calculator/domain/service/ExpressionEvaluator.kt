package com.github.greysteklo.anotherone.calculator.domain.service

interface ExpressionEvaluator {
    fun evaluate(expression: String): Result<Double>

    fun formatResult(result: Double): String
}
