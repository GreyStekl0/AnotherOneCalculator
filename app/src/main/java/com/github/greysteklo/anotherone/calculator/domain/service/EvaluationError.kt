package com.github.greysteklo.anotherone.calculator.domain.service

sealed class EvaluationError(
    override val message: String?,
    override val cause: Throwable?,
) : Throwable(message, cause) {
    data class InvalidExpression(
        override val cause: Throwable,
    ) : EvaluationError("Invalid expression syntax", cause)

    data class CalculationError(
        override val cause: Throwable,
    ) : EvaluationError("Mathematical calculation error", cause)

    data class UnknownError(
        override val cause: Throwable,
    ) : EvaluationError("An unknown error occurred", cause)
}
