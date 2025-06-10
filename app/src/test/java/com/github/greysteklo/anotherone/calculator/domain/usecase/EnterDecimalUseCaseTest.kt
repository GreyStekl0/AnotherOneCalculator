package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EnterDecimalUseCaseTest {
    private lateinit var enterDecimalUseCase: EnterDecimalUseCase

    @BeforeEach
    fun setUp() {
        enterDecimalUseCase = EnterDecimalUseCase()
    }

    @Test
    fun `EnterDecimalUseCase not adds decimal point when last character is decimal point`() {
        val expression = "123,"
        val result = enterDecimalUseCase.execute(expression)
        assert(result == "123,")
    }

    @ParameterizedTest
    @ValueSource(strings = ["123+", "123-", "123×", "123÷"])
    fun `EnterDecimalUseCase not adds decimal point when last character is an operation`(expression: String) {
        val result = enterDecimalUseCase.execute(expression)
        assert(result == expression)
    }

    @Test
    fun `EnterDecimalUseCase adds decimal point when last character is a number`() {
        val expression = "123"
        val result = enterDecimalUseCase.execute(expression)
        assert(result == "123,")
    }

    @Test
    fun `EnterDecimalUseCase not adds decimal point when expression with decimal point`() {
        val expression = "123,456"
        val result = enterDecimalUseCase.execute(expression)
        assert(result == "123,456")
    }
}
