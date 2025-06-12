package com.github.greysteklo.anotherone.calculator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.greysteklo.anotherone.calculator.data.local.entity.CalculationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculationDao {
    @Insert
    suspend fun insert(calculationEntity: CalculationEntity)

    @Query("SELECT * FROM calculation_history ORDER BY id DESC")
    fun getAllHistory(): Flow<List<CalculationEntity>>

    @Query("DELETE FROM calculation_history")
    suspend fun clearHistory()
}
