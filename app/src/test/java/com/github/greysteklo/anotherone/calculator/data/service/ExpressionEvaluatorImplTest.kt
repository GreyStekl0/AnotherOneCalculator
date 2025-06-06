package com.github.greysteklo.anotherone.calculator.data.service

import com.ezylang.evalex.EvaluationException
import com.github.greysteklo.anotherone.calculator.domain.service.EvaluationError
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ExpressionEvaluatorImplTest {
    private lateinit var expressionEvaluator: ExpressionEvaluator

    @BeforeEach
    fun setUp() {
        val numberFormat =
            NumberFormat
                .getNumberInstance(Locale("ru", "RU"))
                .apply { maximumFractionDigits = Int.MAX_VALUE }
        expressionEvaluator = ExpressionEvaluatorImpl(numberFormat)
    }

    @Test
    fun `evaluate simple expression`() {
        val expression = "2 + 2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(4))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with spaces`() {
        val expression = "         2   +      2        "
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(4))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with ru locale`() {
        val expression = "2,5 + 2,5"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(5))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate unfinished expression`() {
        val expression = "2 + 2 +"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(4))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with division`() {
        val expression = "2 ÷ 2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(1))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with multiplication`() {
        val expression = "2 × 2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(4))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with parentheses`() {
        val expression = "(2 + 2) * 2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(8))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with unfinished parentheses`() {
        val expression = "2(2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(4))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with negative number`() {
        val expression = "-2 + 2"
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal(0))
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with zero`() {
        val expression = ""
        val result = expressionEvaluator.evaluate(expression)
        val expectedResult = Result.success(BigDecimal.ZERO)
        assertEquals(result, expectedResult)
    }

    @Test
    fun `evaluate expression with division by zero`() {
        val expression = "2 / 0"
        val result = expressionEvaluator.evaluate(expression)

        assertTrue(result.isFailure)

        val error = result.exceptionOrNull()
        assertTrue(error is EvaluationError.CalculationError)

        val cause = (error as EvaluationError.CalculationError).cause
        assertTrue(cause is EvaluationException)

        assertEquals("Division by zero", cause.message)
    }

    @Test
    fun `format result with scale`() {
        val result = BigDecimal("3.000")
        val formattedResult = expressionEvaluator.formatResult(result)
        assertEquals("3,000", formattedResult)
    }

    @Test
    fun `format result with zero scale`() {
        val result = BigDecimal("3.0000").setScale(0)
        val formattedResult = expressionEvaluator.formatResult(result)
        assertEquals("3", formattedResult)
    }
}
