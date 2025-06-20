package com.github.greysteklo.anotherone.calculator.data.remote.api

import retrofit2.http.GET

interface RuJokeApiService {
    @GET("Rand.aspx?CType=1")
    suspend fun getRandomJoke(): String
}
