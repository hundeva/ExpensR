package com.hdeva.expensr.service

import com.hdeva.expensr.domain.model.Transaction
import java.text.SimpleDateFormat
import javax.inject.Inject

class DateFormatter @Inject constructor() {
    fun formatTransactionHeaderDate(transactions: List<Transaction>): String {
        return transactions.firstOrNull()?.let { transaction ->
            SimpleDateFormat.getDateInstance().format(transaction.calendar.time)
        } ?: ""
    }
}
