package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import javax.inject.Inject

class EnterEquallyUseCase
    @Inject
    constructor() {
        fun execute(calculatorState: CalculatorState): CalculatorState =
            if (calculatorState.result != "Error") {
                CalculatorState(
                    expression = calculatorState.result,
                    result = calculatorState.result,
                )
            } else {
                calculatorState
            }
    }
