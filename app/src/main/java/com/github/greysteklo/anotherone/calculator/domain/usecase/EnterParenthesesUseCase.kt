package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterParenthesesUseCase
    @Inject
    constructor() {
        fun execute(expression: String): String {
            val openParenthesesCount = expression.count { it == '(' }
            val closeParenthesesCount = expression.count { it == ')' }

            return if (expression == "0") {
                "("
            } else if (expression.last() in "(+-*/") {
                "$expression("
            } else if (openParenthesesCount > closeParenthesesCount) {
                "$expression)"
            } else {
                "$expression("
            }
        }
    }
