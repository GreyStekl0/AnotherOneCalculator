package com.github.greysteklo.anotherone.calculator.data.service

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException
import java.util.EmptyStackException
import java.util.Locale
import javax.inject.Inject

class ExpressionEvaluatorImpl
    @Inject
    constructor() : ExpressionEvaluator {
        override fun evaluate(expression: String): Result<Double> =
            try {
                val preparedExpression = prepareExpressionForEvaluation(expression)
                if (preparedExpression.isEmpty()) {
                    Result.success(0.0)
                } else {
                    val resultValue = ExpressionBuilder(preparedExpression).build().evaluate()
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

        override fun formatResult(result: Double): String =
            if (result == result.toLong().toDouble()) {
                result.toLong().toString()
            } else {
                String
                    .format(Locale.US, "%.12f", result)
                    .replace("0*$".toRegex(), "")
                    .replace(".", ",")
            }
    }
