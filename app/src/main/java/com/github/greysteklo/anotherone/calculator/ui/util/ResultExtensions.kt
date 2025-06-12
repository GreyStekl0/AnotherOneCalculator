package com.github.greysteklo.anotherone.calculator.ui.util

import timber.log.Timber

inline fun <T> Result<T>.logOnFailure(message: (error: Throwable) -> String): Result<T> {
    onFailure { error ->
        Timber.e(error, message(error))
    }
    return this
}
