package com.hdeva.expensr.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdeva.expensr.domain.dao.TransactionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteTransactionViewModel @Inject constructor(
    private val dao: TransactionDao,
) : ViewModel() {

    fun deleteTransaction(transactionId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteTransaction(transactionId)
        }
    }
}
