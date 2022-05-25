package com.hdeva.expensr.ui.transaction

import androidx.lifecycle.ViewModel
import com.hdeva.expensr.domain.dao.TransactionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val dao: TransactionDao,
): ViewModel() {
}
