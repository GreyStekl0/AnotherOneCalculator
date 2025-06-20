package com.github.greysteklo.anotherone.calculator.domain.repository

import com.github.greysteklo.anotherone.calculator.domain.model.Joke

interface JokeRepository {
    suspend fun getJoke(): Result<Joke>
}
