package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import javax.inject.Inject

class CalculateExpressionUseCase
    @Inject
    constructor(
        private val expressionEvaluator: ExpressionEvaluator,
    ) {
        fun execute(expression: String): CalculatorState =
            try {
                expressionEvaluator.evaluate(expression).fold(
                    onSuccess = { result ->
                        val formattedResult = expressionEvaluator.formatResult(result)
                        CalculatorState(expression = expression, result = formattedResult)
                    },
                    onFailure = {
                        CalculatorState(expression = expression, result = "Error")
                    },
                )
            } catch (_: Exception) {
                CalculatorState(expression = expression, result = "Error")
            }
    }
