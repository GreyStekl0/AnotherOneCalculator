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
    fun `execute returns CalculatorState with empty expression and result`() {
        val calculatorState = clearAllUseCase.execute()
        assertEquals("", calculatorState.expression)
        assertEquals("", calculatorState.result)
    }
}
