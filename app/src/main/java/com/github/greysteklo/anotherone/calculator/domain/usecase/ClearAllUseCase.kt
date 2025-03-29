package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.valueobject.CalculatorState
import javax.inject.Inject

class ClearAllUseCase
    @Inject
    constructor() {
        fun execute(): CalculatorState = CalculatorState()
    }
