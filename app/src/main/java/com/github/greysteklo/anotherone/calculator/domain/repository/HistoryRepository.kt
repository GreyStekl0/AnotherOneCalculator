package com.github.greysteklo.anotherone.calculator.domain.repository

import com.github.greysteklo.anotherone.calculator.domain.model.SavedCalculation
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveCalculation(savedCalculation: SavedCalculation)

    fun getHistory(): Flow<List<SavedCalculation>>

    suspend fun clearHistory()
}
