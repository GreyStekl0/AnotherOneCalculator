package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterNumberUseCase
    @Inject
    constructor() {
        fun execute(
            expression: String,
            number: Int,
        ): String {
            return if (expression == "0") {
                number.toString()
            } else if (isLastNumberInteger(expression)) {
                "$expression $number"
            } else {
                return expression + number.toString()
            }
        }
    }

private fun isLastNumberInteger(expression: String): Boolean {
    if (expression.length < 3 || expression.takeLast(3).any { it in "+-×÷ " }) {
        return false
    }
    val regex = """([\d,]+)$""".toRegex()
    val matchResult = regex.find(expression)

    return matchResult?.let {
        val lastNumberStr = it.groupValues[1]
        !lastNumberStr.contains(',')
    } == true
}
