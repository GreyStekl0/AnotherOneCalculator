package com.github.greysteklo.anotherone.calculator.di

import com.github.greysteklo.anotherone.calculator.data.service.ExpressionEvaluatorImpl
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    @Singleton
    abstract fun bindExpressionEvaluator(impl: ExpressionEvaluatorImpl): ExpressionEvaluator
}

@Module
@InstallIn(SingletonComponent::class)
object ServiceProvidersModule {
    @Provides
    fun provideNumberFormat(): NumberFormat =
        NumberFormat
            .getNumberInstance(Locale("ru", "RU"))
            .apply { maximumFractionDigits = Int.MAX_VALUE }
}
