package com.github.greysteklo.anotherone.calculator.data.remote.api

import com.github.greysteklo.anotherone.calculator.data.remote.dto.JokeDto
import retrofit2.http.GET

interface EngJokeApiService {
    @GET("joke/Any")
    suspend fun getRandomJoke(): JokeDto
}
