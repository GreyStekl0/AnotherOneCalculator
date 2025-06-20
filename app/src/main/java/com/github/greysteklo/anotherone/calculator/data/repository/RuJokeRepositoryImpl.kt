package com.github.greysteklo.anotherone.calculator.data.repository

import com.github.greysteklo.anotherone.calculator.data.remote.api.RuJokeApiService
import com.github.greysteklo.anotherone.calculator.domain.model.Joke
import com.github.greysteklo.anotherone.calculator.domain.repository.JokeRepository
import javax.inject.Inject

class RuJokeRepositoryImpl
    @Inject
    constructor(
        private val api: RuJokeApiService,
    ) : JokeRepository {
        override suspend fun getJoke(): Result<Joke> =
            try {
                val jokeText = api.getRandomJoke()
                Result.success(Joke(text = jokeText))
            } catch (e: Exception) {
                Result.failure(e)
            }
    }
