package com.github.greysteklo.anotherone.calculator.domain.usecase

class EnterOperationUseCase {
    fun execute(
        expression: String,
        operation: String,
    ): String = expression + operation
}
