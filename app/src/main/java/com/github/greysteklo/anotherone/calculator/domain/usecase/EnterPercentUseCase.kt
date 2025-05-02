package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import com.github.greysteklo.anotherone.calculator.domain.valueobject.CalculatorState
import java.math.BigDecimal
import javax.inject.Inject

class EnterPercentUseCase
    @Inject
    constructor(
        private val expressionEvaluator: ExpressionEvaluator,
    ) {
        fun execute(expression: String): CalculatorState =
            try {
                expressionEvaluator.evaluate(expression).fold(
                    onSuccess = { result ->
                        val percentValue = result.divide(BigDecimal(100))
                        val formattedResult = expressionEvaluator.formatResult(percentValue)
                        CalculatorState(expression = formattedResult, result = formattedResult)
                    },
                    onFailure = {
                        CalculatorState(expression = expression, result = "Error")
                    },
                )
            } catch (_: Exception) {
                CalculatorState(expression = expression, result = "Error")
            }
    }
