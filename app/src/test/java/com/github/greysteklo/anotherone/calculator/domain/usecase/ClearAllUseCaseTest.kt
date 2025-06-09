package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClearAllUseCaseTest {
    private lateinit var clearAllUseCase: ClearAllUseCase

    @BeforeEach
    fun setUp() {
        clearAllUseCase = ClearAllUseCase()
    }

    @Test
    fun `execute returns CalculatorState with zero expression and result`() {
        val calculatorState = clearAllUseCase.execute()
        assertEquals("0", calculatorState.expression)
        assertEquals("0", calculatorState.result)
    }
}
