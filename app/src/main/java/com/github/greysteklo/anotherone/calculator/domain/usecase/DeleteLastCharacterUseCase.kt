package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.util.getLastNumber
import java.text.NumberFormat
import javax.inject.Inject

class DeleteLastCharacterUseCase
    @Inject
    constructor(
        private val numberFormat: NumberFormat,
    ) {
        fun execute(expression: String): String =
            if (expression.length <= 1) {
                "0"
            } else {
                val lastNumber = getLastNumber(expression)
                if (lastNumber == null || lastNumber.length == 1) {
                    expression.dropLast(1)
                } else {
                    val prefix = expression.dropLast(lastNumber.length)
                    val newLastNumber =
                        lastNumber
                            .dropLast(1)
                            .replace(Regex("\\s+"), "")
                            .replace(",", ".")
                            .toBigDecimal()

                    numberFormat.minimumFractionDigits = newLastNumber.scale()
                    numberFormat.maximumFractionDigits = newLastNumber.scale()

                    prefix + numberFormat.format(newLastNumber)
                }
            }
    }
