package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EnterParenthesesUseCaseTest {
    private lateinit var enterParenthesesUseCase: EnterParenthesesUseCase

    @BeforeEach
    fun setUp() {
        enterParenthesesUseCase = EnterParenthesesUseCase()
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression is zero`() {
        val expression = "0"
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "(")
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression ends with operation`() {
        val expression = "1+2"
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "1+2(")
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression has open parentheses`() {
        val expression = "(1+2"
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "(1+2)")
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression has open and close parentheses`() {
        val expression = "(1+2)"
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "(1+2)(")
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression ends parentheses`() {
        val expression = "1("
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "1((")
    }

    @Test
    fun `EnterParenthesesUseCase returns correct string when expression closes several brackets`() {
        val expression = "((1)"
        val result = enterParenthesesUseCase.execute(expression)
        assertEquals(result, "((1))")
    }
}
