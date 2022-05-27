package com.hdeva.expensr.service

import android.content.Context
import com.hdeva.expensr.R
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import javax.inject.Inject

class TransactionHelper @Inject constructor() {

    fun formatTransactionDescription(context: Context, transaction: Transaction): String {
        return transaction.description.ifEmpty {
            context.getString(R.string.no_description)
        }
    }

    fun formatTransactionValue(transaction: Transaction): String {
        val prefix = if (transaction.type == TransactionType.INCOME) "" else "-"
        return "$prefix\$${transaction.value}"
    }
}
