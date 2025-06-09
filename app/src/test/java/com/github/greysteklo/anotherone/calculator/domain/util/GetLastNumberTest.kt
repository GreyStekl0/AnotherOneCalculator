package com.github.greysteklo.anotherone.calculator.domain.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull

class GetLastNumberTest {
    @Test
    fun `getLastNumber returns null for empty string`() {
        val expression = ""
        val result = getLastNumber(expression)
        assertNull(result)
    }

    @Test
    fun `getLastNumber returns number for valid expression`() {
        val expression = "100"
        val result = getLastNumber(expression)
        assertEquals("100", result)
    }

    @Test
    fun `getLastNumber returns null for valid expression with spaces`() {
        val expression = "100   "
        val result = getLastNumber(expression)
        assertNull(result)
    }

    @Test
    fun `getLastNumber returns number for valid expression with commas`() {
        val expression = "1,000"
        val result = getLastNumber(expression)
        assertEquals("1,000", result)
    }

    @Test
    fun `getLastNumber returns number for valid expression with thousands separator`() {
        val expression = "1 000"
        val result = getLastNumber(expression)
        assertEquals("1 000", result)
    }

    @Test
    fun `getLastNumber returns number for valid expression with thousands separator and commas`() {
        val expression = "1 000,00"
        val result = getLastNumber(expression)
        assertEquals("1 000,00", result)
    }

    @Test
    fun `getLastNumber returns number for expression`() {
        val expression = "1 + 2"
        val result = getLastNumber(expression)
        assertEquals("2", result)
    }

    @Test
    fun `getLastNumber returns number for expression with commas`() {
        val expression = "1 + 2,5"
        val result = getLastNumber(expression)
        assertEquals("2,5", result)
    }

    @Test
    fun `getLastNumber returns number for expression with thousands separator`() {
        val expression = "1 000 + 2 000"
        val result = getLastNumber(expression)
        assertEquals("2 000", result)
    }

    @Test
    fun `getLastNumber returns number for expression with thousands separator and commas`() {
        val expression = "1 000,00 + 2 000,00"
        val result = getLastNumber(expression)
        assertEquals("2 000,00", result)
    }
}
