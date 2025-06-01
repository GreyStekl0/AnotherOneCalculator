package com.github.greysteklo.anotherone.calculator.domain.util

fun getLastNumber(expression: String): String? {
    val regex = """(\d{1,3}(?:\s\d{3})*(?:,\d+)?)$""".toRegex()
    val matchResult = regex.find(expression)
    return matchResult?.groupValues?.get(1)
}
