package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EnterOperationUseCaseTest {
    private lateinit var enterOperationUseCase: EnterOperationUseCase

    @BeforeEach
    fun setUp() {
        enterOperationUseCase = EnterOperationUseCase()
    }

    @Test
    fun `execute returns correct string when expression is not empty`() {
        val expression = "100"
        val operation = "+"
        val result = enterOperationUseCase.execute(expression, operation)
        assertEquals(result, "100+")
    }

    @Test
    fun `execute returns expression when expression ending with operation`() {
        val expression = "100+"
        val operation = "+"
        val result = enterOperationUseCase.execute(expression, operation)
        assertEquals(result, "100+")
    }

    @Test
    fun `execute returns expression wight operation when expression ending with operation`() {
        val expression = "100×"
        val operation = "+"
        val result = enterOperationUseCase.execute(expression, operation)
        assertEquals(result, "100+")
    }
}
