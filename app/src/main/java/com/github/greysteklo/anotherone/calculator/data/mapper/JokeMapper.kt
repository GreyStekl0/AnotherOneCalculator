package com.github.greysteklo.anotherone.calculator.data.mapper

import com.github.greysteklo.anotherone.calculator.data.remote.dto.JokeDto
import com.github.greysteklo.anotherone.calculator.domain.model.Joke

fun JokeDto.toDomainModel(): Joke {
    val jokeText =
        if (type == "twopart") {
            "$setup\n$delivery"
        } else {
            joke ?: "No joke found"
        }
    return Joke(text = jokeText)
}
