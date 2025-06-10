package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.NumberFormat
import java.util.Locale

class EnterNumberUseCaseTest {
    private lateinit var enterNumberUseCase: EnterNumberUseCase

    @BeforeEach
    fun setUp() {
        val numberFormat =
            NumberFormat
                .getNumberInstance(Locale("ru", "RU"))
                .apply { maximumFractionDigits = Int.MAX_VALUE }
        enterNumberUseCase = EnterNumberUseCase(numberFormat)
    }

    @Test
    fun `execute returns correct string when expression is empty`() {
        val expression = ""
        val number = 1
        val result = enterNumberUseCase.execute(expression, number)
        assertEquals(result, "1")
    }

    @Test
    fun `execute returns correct string when expression is not empty`() {
        val expression = "1"
        val number = 2
        val result = enterNumberUseCase.execute(expression, number)
        assertEquals(result, "12")
    }

    @Test
    fun `execute returns correct string when expression length is three`() {
        val expression = "100"
        val number = 3
        val result = enterNumberUseCase.execute(expression, number)
        assertEquals(result, "1 003")
    }

    @Test
    fun `execute returns correct string when expression has decimal point`() {
        val expression = "10,000"
        val number = 3
        val result = enterNumberUseCase.execute(expression, number)
        assertEquals(result, "10,0003")
    }
}
