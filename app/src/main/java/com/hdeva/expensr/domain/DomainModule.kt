package com.hdeva.expensr.domain

import android.content.Context
import androidx.room.Room
import com.hdeva.expensr.domain.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExpensRDatabase {
        return Room.databaseBuilder(
            context,
            ExpensRDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: ExpensRDatabase): TransactionDao {
        return database.transactionDao()
    }

    companion object {
        private const val DATABASE_NAME = "expensr-database.db"
    }
}
