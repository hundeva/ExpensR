package com.hdeva.expensr.service

import com.hdeva.expensr.domain.model.Transaction
import javax.inject.Inject

class TransactionSorter @Inject constructor() {
    fun groupAndSortTransactions(transactions: List<Transaction>): List<List<Transaction>> {
        return transactions.asSequence()
            .sortedByDescending { transaction -> transaction.calendar.timeInMillis }
            .groupBy { transaction -> transaction.groupIndex }
            .toList()
            .sortedByDescending { pair -> pair.first }
            .map { pair -> pair.second }
            .toList()
    }
}
