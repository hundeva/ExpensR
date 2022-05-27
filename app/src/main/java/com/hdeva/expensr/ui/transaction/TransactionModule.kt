package com.hdeva.expensr.ui.transaction

import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TransactionModule {

    @Provides
    @Singleton
    @Named(TRANSACTION_RECYCLED_VIEW_POOL)
    fun provideSharedRecycledViewPool(): RecyclerView.RecycledViewPool {
        return RecyclerView.RecycledViewPool()
    }

    companion object {
        const val TRANSACTION_RECYCLED_VIEW_POOL = "transaction_recycled_view_pool"
    }
}
