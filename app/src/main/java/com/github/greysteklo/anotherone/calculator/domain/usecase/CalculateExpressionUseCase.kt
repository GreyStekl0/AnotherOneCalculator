package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import javax.inject.Inject

class CalculateExpressionUseCase
    @Inject
    constructor(
        private val expressionEvaluator: ExpressionEvaluator,
    ) {
        fun execute(expression: String): Result<String> =
            expressionEvaluator.evaluate(expression).fold(
                onSuccess = { result ->
                    val formattedResult = expressionEvaluator.formatResult(result)
                    Result.success(formattedResult)
                },
                onFailure = { error ->
                    Result.failure(error)
                },
            )
    }
