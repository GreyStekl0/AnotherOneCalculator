package com.github.greysteklo.anotherone.calculator.data.repository

import com.github.greysteklo.anotherone.calculator.data.local.dao.CalculationDao
import com.github.greysteklo.anotherone.calculator.data.mapper.toDomain
import com.github.greysteklo.anotherone.calculator.data.mapper.toEntity
import com.github.greysteklo.anotherone.calculator.domain.model.SavedCalculation
import com.github.greysteklo.anotherone.calculator.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl
    @Inject
    constructor(
        private val calculationDao: CalculationDao,
    ) : HistoryRepository {
        override suspend fun saveCalculation(savedCalculation: SavedCalculation) {
            val entity = savedCalculation.toEntity()
            calculationDao.insert(entity)
        }

        override fun getHistory(): Flow<List<SavedCalculation>> =
            calculationDao.getAllHistory().map { entities ->
                entities.map { it.toDomain() }
            }

        override suspend fun clearHistory() {
            calculationDao.clearHistory()
        }
    }
