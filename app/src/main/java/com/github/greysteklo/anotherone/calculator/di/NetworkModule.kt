package com.github.greysteklo.anotherone.calculator.di

import com.github.greysteklo.anotherone.calculator.data.remote.api.EngJokeApiService
import com.github.greysteklo.anotherone.calculator.data.remote.api.RuJokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.nio.charset.Charset
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @EngJokeApi
    fun provideEngJokeApiRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideEngJokeApiService(
        @EngJokeApi retrofit: Retrofit,
    ): EngJokeApiService = retrofit.create(EngJokeApiService::class.java)

    @Provides
    @Singleton
    @RuJokeApi
    fun provideRuJokeApiOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                val body = originalResponse.body

                val rawXmlString = body?.bytes()?.toString(Charset.forName("windows-1251")) ?: ""

                val cleanText =
                    rawXmlString
                        .substringAfter("<content>", "")
                        .substringBefore("</content>", "")
                        .trim()

                val newBody = cleanText.toResponseBody(body?.contentType())

                originalResponse.newBuilder().body(newBody).build()
            }.build()

    @Provides
    @Singleton
    @RuJokeApi
    fun provideRuJokeApiRetrofit(
        @RuJokeApi okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://rzhunemogu.ru/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRuJokeApiApiService(
        @RuJokeApi retrofit: Retrofit,
    ): RuJokeApiService = retrofit.create(RuJokeApiService::class.java)
}
