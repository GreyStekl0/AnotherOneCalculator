package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.valueobject.CalculatorState
import javax.inject.Inject

class EnterEquallyUseCase
    @Inject
    constructor() {
        fun execute(calculatorState: CalculatorState): CalculatorState =
            CalculatorState(
                expression = calculatorState.result,
                result = calculatorState.result,
            )
    }
