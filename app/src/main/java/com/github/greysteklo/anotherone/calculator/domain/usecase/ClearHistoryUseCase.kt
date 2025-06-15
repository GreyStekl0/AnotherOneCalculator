package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.repository.HistoryRepository
import javax.inject.Inject

class ClearHistoryUseCase
    @Inject
    constructor(
        private val repository: HistoryRepository,
    ) {
        suspend operator fun invoke() = repository.clearHistory()
    }
