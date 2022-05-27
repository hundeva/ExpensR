package com.hdeva.expensr.ui.home

import androidx.lifecycle.*
import com.hdeva.expensr.domain.dao.TransactionDao
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import com.hdeva.expensr.service.TransactionSorter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.min
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionSorter: TransactionSorter,
    private val transactionDao: TransactionDao,
) : ViewModel() {

    val allTransactions = transactionDao.getAllTransactions().asLiveData().distinctUntilChanged()
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

    fun groupAndSortTransactions(transactions: List<Transaction>): List<List<Transaction>> {
        return transactionSorter.groupAndSortTransactions(transactions)
    }

    private fun calculateBalance() = (allIncome.value ?: 0) - (allExpenses.value ?: 0)

    private fun calculateBalanceRatio(): Int {
        val ratio = (allExpenses.value ?: 0).toDouble() / (allIncome.value ?: 0).toDouble()
        return min((ratio * 100).toInt(), 100)
    }

    // help for testing
    fun addRandomData() {
        viewModelScope.launch(Dispatchers.IO) {
            repeat(20) {
                try {
                    transactionDao.insertTransaction(
                        Transaction(
                            type = if (Random.nextBoolean()) TransactionType.EXPENSE else TransactionType.INCOME,
                            value = Random.nextInt(100),
                            description = UUID.randomUUID().toString(),
                            calendar = Calendar.getInstance().apply {
                                add(Calendar.DATE, -Random.nextInt(5))
                                add(Calendar.HOUR_OF_DAY, -Random.nextInt(3))
                                add(Calendar.MINUTE, -Random.nextInt(30))
                            },
                        )
                    )
                } catch (throwable: Throwable) {
                    // ignored
                }
            }
        }
    }

    // help for testing
    fun clearData() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.clearTransactions()
        }
    }
}
