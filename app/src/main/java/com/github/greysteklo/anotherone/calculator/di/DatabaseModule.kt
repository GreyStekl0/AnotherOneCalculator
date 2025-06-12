package com.github.greysteklo.anotherone.calculator.di

import android.content.Context
import androidx.room.Room
import com.github.greysteklo.anotherone.calculator.data.local.HistoryDatabase
import com.github.greysteklo.anotherone.calculator.data.local.dao.CalculationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideHistoryDatabase(
        @ApplicationContext context: Context,
    ): HistoryDatabase =
        Room
            .databaseBuilder(
                context,
                HistoryDatabase::class.java,
                "calculation_history.db",
            ).build()

    @Provides
    @Singleton
    fun provideHistoryDao(database: HistoryDatabase): CalculationDao = database.calculationDao()
}
