package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterNumberUseCase
    @Inject
    constructor() {
        fun execute(
            expression: String,
            number: Int,
        ): String = if (expression == "0") number.toString() else expression + number.toString()
    }
