package com.github.greysteklo.anotherone.calculator.di

import com.github.greysteklo.anotherone.calculator.data.repository.HistoryRepositoryImpl
import com.github.greysteklo.anotherone.calculator.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}
