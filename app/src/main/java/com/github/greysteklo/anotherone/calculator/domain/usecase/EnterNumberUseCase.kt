package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.util.getLastNumber
import java.text.NumberFormat
import javax.inject.Inject

class EnterNumberUseCase
    @Inject
    constructor(
        private val numberFormat: NumberFormat,
    ) {
        fun execute(
            expression: String,
            number: Int,
        ): String {
            if (expression == "0") return number.toString()

            val lastNumber = getLastNumber(expression) ?: return expression + number
            val prefix = expression.dropLast(lastNumber.length)

            val appended = lastNumber + number
            val newLastNumber =
                appended
                    .replace(Regex("\\s+"), "")
                    .replace(",", ".")
                    .toBigDecimal()

            numberFormat.minimumFractionDigits = newLastNumber.scale()
            numberFormat.maximumFractionDigits = newLastNumber.scale()

            return prefix + numberFormat.format(newLastNumber)
        }
    }
