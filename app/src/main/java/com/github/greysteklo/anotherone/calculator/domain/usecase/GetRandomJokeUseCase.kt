package com.github.greysteklo.anotherone.calculator.domain.usecase

import com.github.greysteklo.anotherone.calculator.domain.repository.JokeRepository
import javax.inject.Inject

class GetRandomJokeUseCase
    @Inject
    constructor(
        private val repository: JokeRepository,
    ) {
        suspend operator fun invoke() = repository.getJoke()
    }
