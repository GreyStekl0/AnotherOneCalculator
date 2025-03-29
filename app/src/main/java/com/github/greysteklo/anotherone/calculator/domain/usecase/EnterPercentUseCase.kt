package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import javax.inject.Inject

class EnterPercentUseCase
    @Inject
    constructor(
        private val expressionEvaluator: ExpressionEvaluator,
    ) {
        fun execute(expression: String): String =
            if (expression.last() in "+-*/") {
                expression.dropLast(1) + "/100"
            } else {
                "$expression/100"
            }
    }
