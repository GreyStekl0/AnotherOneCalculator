package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterDecimalUseCase
    @Inject
    constructor() {
        fun execute(expression: String): String {
            val lastChar = expression.last()
            if (lastChar == ',' || lastChar in "+-×÷") {
                return expression
            }

            val lastOperatorIndex =
                expression.indexOfLast { it in "+-×÷" }

            val lastNumberSegment =
                if (lastOperatorIndex == -1) {
                    expression
                } else {
                    expression.substring(lastOperatorIndex + 1)
                }

            return if (lastNumberSegment.contains(',')) {
                expression
            } else {
                "$expression,"
            }
        }
    }
