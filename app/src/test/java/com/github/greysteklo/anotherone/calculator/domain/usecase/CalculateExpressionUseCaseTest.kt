package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateExpressionUseCaseTest {
    private lateinit var calculateExpressionUseCase: CalculateExpressionUseCase
    private lateinit var expressionEvaluator: ExpressionEvaluator

    @BeforeEach
    fun setUp() {
        expressionEvaluator = mockk<ExpressionEvaluator>()
        calculateExpressionUseCase = CalculateExpressionUseCase(expressionEvaluator)
    }

    @Test
    fun `CalculateExpressionUseCase calculates expression correctly`() {
        val expression = "2 + 3"
        val expectedResult = "5"

        every { expressionEvaluator.evaluate(expression) } returns
            Result.success(
                BigDecimal(
                    expectedResult,
                ),
            )
        every { expressionEvaluator.formatResult(any()) } returns expectedResult

        val calculatorState = calculateExpressionUseCase.execute(expression)
        assertEquals(calculatorState.result, expectedResult)
        assertEquals(calculatorState.expression, expression)
    }

    @Test
    fun `CalculateExpressionUseCase handles evaluation error`() {
        val expression = "2 + 3"
        val expectedResult = "Error"

        every { expressionEvaluator.evaluate(expression) } returns
            Result.failure(Exception("Evaluation error"))
        every { expressionEvaluator.formatResult(any()) } returns expectedResult
        val calculatorState = calculateExpressionUseCase.execute(expression)

        assertEquals(calculatorState.result, expectedResult)
        assertEquals(calculatorState.expression, expression)
    }
}
