package com.hdeva.expensr.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("DELETE FROM `transaction` WHERE id is :id")
    suspend fun deleteTransaction(id: Int)

    @Query("SELECT * FROM `transaction` ORDER BY id ASC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT SUM(value) FROM `transaction` WHERE type IS :type")
    fun getSumOfTransactionType(type: String): Flow<Int>
}
