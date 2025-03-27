package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import net.objecthunter.exp4j.ExpressionBuilder

class CalculateExpressionUseCase {
    fun execute(expression: String): CalculatorState =
        try {
            val formattedExpression = expression.replace(",", ".")
            val result = ExpressionBuilder(formattedExpression).build().evaluate()
            val formattedResult =
                if (result == result.toLong().toDouble()) {
                    result.toLong().toString()
                } else {
                    result.toString().replace(".", ",")
                }
            CalculatorState(expression = expression, result = formattedResult)
        } catch (_: Exception) {
            CalculatorState(expression = expression, result = "Error")
        }
}
