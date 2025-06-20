package com.github.greysteklo.anotherone.calculator.data.repository

import com.github.greysteklo.anotherone.calculator.domain.model.Joke
import com.github.greysteklo.anotherone.calculator.domain.repository.JokeRepository
import timber.log.Timber

class JokeRepositoryImpl<T>(
    private val fetcher: suspend () -> T,
    private val mapper: (T) -> Joke,
) : JokeRepository {
    override suspend fun getJoke(): Result<Joke> =
        try {
            val rawData = fetcher()
            val joke = mapper(rawData)
            Result.success(joke)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
}
