package com.github.greysteklo.anotherone.calculator.domain.usecase

class DeleteLastCharacterUseCase {
    fun execute(expression: String): String = if (expression.length <= 1) "0" else expression.dropLast(1)
}
