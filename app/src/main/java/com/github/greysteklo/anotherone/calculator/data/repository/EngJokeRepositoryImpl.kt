package com.github.greysteklo.anotherone.calculator.data.repository

import com.github.greysteklo.anotherone.calculator.data.mapper.toDomainModel
import com.github.greysteklo.anotherone.calculator.data.remote.api.EngJokeApiService
import com.github.greysteklo.anotherone.calculator.domain.model.Joke
import com.github.greysteklo.anotherone.calculator.domain.repository.JokeRepository
import javax.inject.Inject

class EngJokeRepositoryImpl
    @Inject
    constructor(
        private val api: EngJokeApiService,
    ) : JokeRepository {
        override suspend fun getJoke(): Result<Joke> =
            try {
                val jokeDto = api.getRandomJoke()
                Result.success(jokeDto.toDomainModel())
            } catch (e: Exception) {
                Result.failure(e)
            }
    }
