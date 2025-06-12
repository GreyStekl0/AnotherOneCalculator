package com.github.greysteklo.anotherone.calculator.domain.repository

import com.github.greysteklo.anotherone.calculator.domain.model.Calculation
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveCalculation(calculation: Calculation)

    fun getHistory(): Flow<List<Calculation>>

    suspend fun clearHistory()
}
