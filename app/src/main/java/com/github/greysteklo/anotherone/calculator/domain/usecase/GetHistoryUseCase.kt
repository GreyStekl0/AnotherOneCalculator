package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.repository.HistoryRepository
import javax.inject.Inject

class GetHistoryUseCase
    @Inject
    constructor(
        private val repository: HistoryRepository,
    ) {
        operator fun invoke() = repository.getHistory()
    }
