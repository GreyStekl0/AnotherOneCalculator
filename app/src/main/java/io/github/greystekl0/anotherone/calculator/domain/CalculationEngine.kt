package io.github.greystekl0.anotherone.calculator.domain

import net.objecthunter.exp4j.ExpressionBuilder

fun calculate(expression: String): String {
    val sanitizedExpression = expression.removeSuffix()

    return try {
        val result = ExpressionBuilder(sanitizedExpression.replace(',', '.')).build().evaluate()
        result.formatResult()
    } catch (_: Exception) {
        "ERROR"
    }
}

private fun String.removeSuffix(): String = if (last() in ",+-*/") dropLast(1) else this

private fun Double.formatResult(): String =
    if (this % 1 == 0.0) {
        toInt().toString()
    } else {
        toString()
    }
