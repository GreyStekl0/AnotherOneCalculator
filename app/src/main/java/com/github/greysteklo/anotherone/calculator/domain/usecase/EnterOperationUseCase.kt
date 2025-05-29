package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterOperationUseCase
    @Inject
    constructor() {
        fun execute(
            expression: String,
            operation: String,
        ): String =
            if (expression.last() in "+-×÷") {
                expression.dropLast(1) + operation
            } else {
                expression + operation
            }
    }
