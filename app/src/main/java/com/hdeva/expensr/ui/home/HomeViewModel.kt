package com.hdeva.expensr.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hdeva.expensr.domain.dao.TransactionDao
import com.hdeva.expensr.domain.model.Currency
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionDao: TransactionDao,
) : ViewModel() {

    val allTransactions = transactionDao.getAllTransactions().asLiveData()

    fun addRandomData() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.insertTransaction(
                Transaction(
                    type = if (Random.nextBoolean()) TransactionType.EXPENSE else TransactionType.INCOME,
                    value = Random.nextInt(1000),
                    description = UUID.randomUUID().toString(),
                    calendar = Calendar.getInstance().apply {
                        set(Calendar.DAY_OF_YEAR, Random.nextInt(365))
                    },
                    currency = if (Random.nextBoolean()) Currency.EUR else Currency.USD,
                )
            )
        }
    }
}
