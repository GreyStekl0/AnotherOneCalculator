package com.github.greysteklo.anotherone.calculator.di

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.github.greysteklo.anotherone.calculator.data.mapper.toDomainModel
import com.github.greysteklo.anotherone.calculator.data.remote.api.EngJokeApiService
import com.github.greysteklo.anotherone.calculator.data.remote.api.RuJokeApiService
import com.github.greysteklo.anotherone.calculator.data.repository.JokeRepositoryImpl
import com.github.greysteklo.anotherone.calculator.domain.model.Joke
import com.github.greysteklo.anotherone.calculator.domain.repository.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JokeRepositoryModule {
    @Provides
    @Singleton
    fun provideJokeRepository(
        @ApplicationContext context: Context,
        ruApi: RuJokeApiService,
        engApi: EngJokeApiService,
    ): JokeRepository {
        val currentLanguage =
            ConfigurationCompat.getLocales(context.resources.configuration)[0]?.language

        return if (currentLanguage == "ru") {
            JokeRepositoryImpl(
                fetcher = { ruApi.getRandomJoke() },
                mapper = { jokeText -> Joke(text = jokeText) },
            )
        } else {
            JokeRepositoryImpl(
                fetcher = { engApi.getRandomJoke() },
                mapper = { jokeDto -> jokeDto.toDomainModel() },
            )
        }
    }
}
