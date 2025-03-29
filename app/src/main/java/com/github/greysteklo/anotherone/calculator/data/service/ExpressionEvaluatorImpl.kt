package com.github.greysteklo.anotherone.calculator.data.service

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException
import java.util.EmptyStackException
import javax.inject.Inject

class ExpressionEvaluatorImpl
    @Inject
    constructor() : ExpressionEvaluator {
        override fun evaluate(expression: String): Result<Double> =
            try {
                val formattedExpression = expression.replace(",", ".")
                Result.success(ExpressionBuilder(formattedExpression).build().evaluate())
            } catch (e: IllegalArgumentException) {
                Result.failure(e)
            } catch (e: ArithmeticException) {
                Result.failure(e)
            } catch (e: EmptyStackException) {
                Result.failure(e)
            } catch (e: UnknownFunctionOrVariableException) {
                Result.failure(e)
            }

        override fun formatResult(result: Double): String =
            if (result == result.toLong().toDouble()) {
                result.toLong().toString()
            } else {
                result.toString().replace(".", ",")
            }
    }
