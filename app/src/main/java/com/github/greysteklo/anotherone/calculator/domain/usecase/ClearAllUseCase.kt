package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState

class ClearAllUseCase {
    fun execute(): CalculatorState = CalculatorState()
}
