package com.github.greysteklo.anotherone.calculator.domain.usecase

import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class EnterNumberUseCase
    @Inject
    constructor() {
        val numberFormat: NumberFormat =
            NumberFormat
                .getNumberInstance(Locale("ru", "RU"))
                .apply { maximumFractionDigits = Int.MAX_VALUE }

        fun execute(
            expression: String,
            number: Int,
        ): String =
            if (expression == "0") {
                number.toString()
            } else {
                val lastNumber = getLastNumber(expression)
                if (lastNumber != null) {
                    val prefix = expression.dropLast(lastNumber.length)
                    val newLastNumber =
                        (lastNumber + number)
                            .replace(Regex("\\s+"), "")
                            .replace(",", ".")
                            .toBigDecimal()
                    prefix + numberFormat.format(newLastNumber)
                } else {
                    expression + number.toString()
                }
            }
    }

private fun getLastNumber(expression: String): String? {
    val regex = """(\d{1,3}(?:\s\d{3})*(?:,\d+)?)$""".toRegex()
    val matchResult = regex.find(expression)
    return matchResult?.groupValues?.get(1)
}
