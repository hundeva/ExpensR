package com.hdeva.expensr.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.hdeva.expensr.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction` ORDER BY id ASC")
    fun getAllTransactions(): Flow<List<Transaction>>
}
