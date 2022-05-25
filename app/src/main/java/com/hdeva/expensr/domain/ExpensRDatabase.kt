package com.hdeva.expensr.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hdeva.expensr.domain.dao.TransactionDao
import com.hdeva.expensr.domain.model.Transaction

@Database(
    entities = [
        Transaction::class,
    ],
    version = 1,
)
abstract class ExpensRDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}
