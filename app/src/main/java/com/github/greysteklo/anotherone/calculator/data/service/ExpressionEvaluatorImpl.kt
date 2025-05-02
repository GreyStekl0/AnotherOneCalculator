package com.github.greysteklo.anotherone.calculator.data.service

import com.ezylang.evalex.Expression
import com.ezylang.evalex.config.ExpressionConfiguration
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException
import java.math.BigDecimal
import java.util.EmptyStackException
import java.util.Locale
import javax.inject.Inject

class ExpressionEvaluatorImpl
    @Inject
    constructor() : ExpressionEvaluator {
        val config: ExpressionConfiguration =
            ExpressionConfiguration
                .builder()
                .decimalPlacesRounding(12) // округление до 12 знаков
                .stripTrailingZeros(true) // убираем лишние нули
                .implicitMultiplicationAllowed(true) // разрешаем 2x
                .locale(Locale("ru"))
                .build()

        override fun evaluate(expression: String): Result<BigDecimal> =
            try {
                val preparedExpression = prepareExpressionForEvaluation(expression)
                if (preparedExpression.isEmpty()) {
                    Result.success(BigDecimal.ZERO)
                } else {
                    val resultValue = Expression(preparedExpression, config).evaluate().numberValue
                    Result.success(resultValue)
                }
            } catch (e: IllegalArgumentException) {
                Result.failure(e)
            } catch (e: ArithmeticException) {
                Result.failure(e)
            } catch (e: EmptyStackException) {
                Result.failure(e)
            } catch (e: UnknownFunctionOrVariableException) {
                Result.failure(e)
            }

        private fun prepareExpressionForEvaluation(expression: String): String {
            var formatted = expression.replace(",", ".").replace("÷", "/").replace("×", "*")

            while (formatted.isNotEmpty() && formatted.last() in "+-*/(") {
                formatted = formatted.dropLast(1)
            }

            val openBrackets = formatted.count { it == '(' }
            val closeBrackets = formatted.count { it == ')' }
            if (openBrackets > closeBrackets) {
                formatted += ")".repeat(openBrackets - closeBrackets)
            }

            return formatted
        }

        override fun formatResult(result: BigDecimal): String = result.toString()
    }
