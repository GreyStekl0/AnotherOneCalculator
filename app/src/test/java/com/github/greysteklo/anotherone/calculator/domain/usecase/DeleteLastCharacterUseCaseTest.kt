package com.github.greysteklo.anotherone.calculator.domain.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.NumberFormat
import java.util.Locale

class DeleteLastCharacterUseCaseTest {
    private lateinit var deleteLastCharacterUseCase: DeleteLastCharacterUseCase

    @BeforeEach
    fun setUp() {
        val numberFormat =
            NumberFormat
                .getNumberInstance(Locale("ru", "RU"))
                .apply { maximumFractionDigits = Int.MAX_VALUE }
        deleteLastCharacterUseCase = DeleteLastCharacterUseCase(numberFormat)
    }

    @Test
    fun `execute returns zero string when expression is empty`() {
        val expression = ""
        val result = deleteLastCharacterUseCase.execute(expression)
        assertEquals("0", result)
    }

    @Test
    fun `execute returns zero string when expression has only one character`() {
        val expression = "1"
        val result = deleteLastCharacterUseCase.execute(expression)
        assertEquals("0", result)
    }

    @Test
    fun `execute returns correct string when expression has multiple characters`() {
        val expression = "1+2"
        val result = deleteLastCharacterUseCase.execute(expression)
        assertEquals("1+", result)
    }

    @Test
    fun `execute returns correct string when expression has multiple digits`() {
        val expression = "10 000"
        val result = deleteLastCharacterUseCase.execute(expression)
        assertEquals("1 000", result)
    }
}
