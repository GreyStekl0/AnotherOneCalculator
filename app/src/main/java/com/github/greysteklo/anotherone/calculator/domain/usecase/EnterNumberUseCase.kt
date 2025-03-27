package com.github.greysteklo.anotherone.calculator.domain.usecase

class EnterNumberUseCase {
    fun execute(
        expression: String,
        number: Int,
    ): String = if (expression == "0") number.toString() else expression + number.toString()
}
