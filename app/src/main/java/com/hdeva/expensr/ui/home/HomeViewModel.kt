package com.hdeva.expensr.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hdeva.expensr.domain.dao.TransactionDao
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.min
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionDao: TransactionDao,
) : ViewModel() {

    val allTransactions = transactionDao.getAllTransactions().asLiveData()
    val allExpenses =
        transactionDao.getSumOfTransactionType(TransactionType.EXPENSE.type).asLiveData()
    val allIncome = transactionDao.getSumOfTransactionType(TransactionType.INCOME.type).asLiveData()
    val balance = MediatorLiveData<Int>().apply {
        addSource(allExpenses) { value = calculateBalance() }
        addSource(allIncome) { value = calculateBalance() }
    }
    val balanceRatio = MediatorLiveData<Int>().apply {
        addSource(allExpenses) { value = calculateBalanceRatio() }
        addSource(allIncome) { value = calculateBalanceRatio() }
    }

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
                )
            )
        }
    }

    private fun calculateBalance() = (allIncome.value ?: 0) - (allExpenses.value ?: 0)

    private fun calculateBalanceRatio(): Int {
        val ratio = (allExpenses.value ?: 0).toDouble() / (allIncome.value ?: 0).toDouble()
        return min((ratio * 100).toInt(), 100)
    }
}
