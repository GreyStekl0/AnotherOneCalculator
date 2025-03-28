package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class DeleteLastCharacterUseCase
    @Inject
    constructor() {
        fun execute(expression: String): String = if (expression.length <= 1) "0" else expression.dropLast(1)
    }
