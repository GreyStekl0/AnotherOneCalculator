package com.github.greysteklo.anotherone.calculator.data.service

import com.ezylang.evalex.EvaluationException
import com.ezylang.evalex.Expression
import com.ezylang.evalex.config.ExpressionConfiguration
import com.ezylang.evalex.parser.ParseException
import com.github.greysteklo.anotherone.calculator.domain.service.EvaluationError
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import timber.log.Timber
import java.math.BigDecimal
import java.math.MathContext
import java.text.NumberFormat
import javax.inject.Inject

class ExpressionEvaluatorImpl
    @Inject
    constructor(
        private val numberFormat: NumberFormat,
    ) : ExpressionEvaluator {
        val config: ExpressionConfiguration =
            ExpressionConfiguration
                .builder()
                .mathContext(MathContext.DECIMAL128)
                .stripTrailingZeros(true)
                .implicitMultiplicationAllowed(true)
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
            } catch (e: Exception) {
                val error =
                    when (e) {
                        is ParseException -> EvaluationError.InvalidExpression(e)
                        is EvaluationException -> EvaluationError.CalculationError(e)
                        else -> EvaluationError.UnknownError(e)
                    }
                Timber.e(error, "Error evaluating expression: $expression")
                Result.failure(error)
            }

        private fun prepareExpressionForEvaluation(expression: String): String {
            var formatted =
                expression
                    .replace(",", ".")
                    .replace("÷", "/")
                    .replace("×", "*")
                    .replace(Regex("\\s+"), "")

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

        override fun formatResult(result: BigDecimal): String {
            val scale = result.scale()
            val fractionDigits = if (scale < 0) 0 else scale
            numberFormat.maximumFractionDigits = fractionDigits
            numberFormat.minimumFractionDigits = fractionDigits
            return numberFormat.format(result)
        }
    }
