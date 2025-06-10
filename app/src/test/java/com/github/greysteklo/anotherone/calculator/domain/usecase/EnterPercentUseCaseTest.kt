package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class EnterPercentUseCaseTest {
    private lateinit var enterPercentUseCase: EnterPercentUseCase
    private lateinit var expressionEvaluator: ExpressionEvaluator

    @BeforeEach
    fun setUp() {
        expressionEvaluator = mockk<ExpressionEvaluator>()
        enterPercentUseCase = EnterPercentUseCase(expressionEvaluator)
    }

    @Test
    fun `EnterPercentUseCase calculates percentage correctly`() {
        val expression = "100"
        val expectedResult = "1"

        every { expressionEvaluator.evaluate(expression) } returns
            Result.success(BigDecimal(expression))
        every { expressionEvaluator.formatResult(expectedResult.toBigDecimal()) } returns expectedResult

        val calculatorState = enterPercentUseCase.execute(expression)
        assertEquals(calculatorState.result, expectedResult)
        assertEquals(calculatorState.expression, expectedResult)
    }
}
