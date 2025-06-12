package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import java.math.BigDecimal
import javax.inject.Inject

class EnterPercentUseCase
    @Inject
    constructor(
        private val expressionEvaluator: ExpressionEvaluator,
    ) {
        fun execute(expression: String): Result<String> =
            try {
                expressionEvaluator.evaluate(expression).fold(
                    onSuccess = { result ->
                        val percentValue = result.divide(BigDecimal(100))
                        val formattedResult = expressionEvaluator.formatResult(percentValue)
                        Result.success(formattedResult)
                    },
                    onFailure = { error ->
                        Result.failure(error)
                    },
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
    }
