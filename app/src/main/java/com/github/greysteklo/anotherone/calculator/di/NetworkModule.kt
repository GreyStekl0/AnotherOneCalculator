package com.github.greysteklo.anotherone.calculator.di

import com.github.greysteklo.anotherone.calculator.data.remote.api.EngJokeApiService
import com.github.greysteklo.anotherone.calculator.data.remote.api.RuJokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @EngJokeApi
    fun provideEngJokeApiOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @EngJokeApi
    fun provideEngJokeApiRetrofit(
        @EngJokeApi okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .client(okHttpClient)
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
                val bodyString = body?.bytes()?.toString(Charset.forName("windows-1251"))

                val newBody = (bodyString ?: "").toResponseBody(body?.contentType())
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
