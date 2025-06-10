package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.valueobject.CalculatorState
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EnterEquallyUseCaseTest {
    private lateinit var enterEquallyUseCase: EnterEquallyUseCase

    @BeforeEach
    fun setUp() {
        enterEquallyUseCase = EnterEquallyUseCase()
    }

    @Test
    fun `EnterEquallyUseCase returns the same state when result is an error`() {
        val calculatorState = CalculatorState(expression = "1+1", result = "Error")

        val result = enterEquallyUseCase.execute(calculatorState)

        assert(result == calculatorState)
    }

    @Test
    fun `EnterEquallyUseCase returns the same state when result is not an error`() {
        val calculatorState = CalculatorState(expression = "1+1", result = "2")

        val result = enterEquallyUseCase.execute(calculatorState)
        val expectedState = CalculatorState(expression = "2", result = "2")

        assert(result == expectedState)
    }
}
