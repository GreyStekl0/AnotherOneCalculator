package com.github.greysteklo.anotherone.calculator.domain.usecase

import javax.inject.Inject

class EnterDecimalUseCase
    @Inject
    constructor() {
        fun execute(expression: String): String = "$expression,"
    }
