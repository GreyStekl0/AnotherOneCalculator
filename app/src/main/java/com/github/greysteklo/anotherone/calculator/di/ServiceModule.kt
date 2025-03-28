package com.github.greysteklo.anotherone.calculator.di

import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluator
import com.github.greysteklo.anotherone.calculator.domain.service.ExpressionEvaluatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    @Singleton
    abstract fun bindExpressionEvaluator(impl: ExpressionEvaluatorImpl): ExpressionEvaluator
}
