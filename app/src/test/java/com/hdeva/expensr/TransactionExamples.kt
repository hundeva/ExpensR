package com.hdeva.expensr

import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import java.util.*

object TransactionExamples {
    val SIMPLE_EXPENSE = Transaction(
        type = TransactionType.EXPENSE,
        value = 123,
        description = "desc",
        calendar = Calendar.getInstance(),
    )

    val SIMPLE_INCOME = Transaction(
        type = TransactionType.INCOME,
        value = 123,
        description = "desc",
        calendar = Calendar.getInstance(),
    )

    val EMPTY_DESCRIPTION = Transaction(
        type = TransactionType.EXPENSE,
        value = 123,
        description = "",
        calendar = Calendar.getInstance(),
    )

    val ZERO_VALUE_ITEM = Transaction(
        type = TransactionType.EXPENSE,
        value = 0,
        description = "desc",
        calendar = Calendar.getInstance(),
    )

    val YESTERDAY_ITEM = Transaction(
        type = TransactionType.EXPENSE,
        value = 123,
        description = "desc",
        calendar = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        },
    )

    val ONE_MINUTE_AGO_ITEM = Transaction(
        type = TransactionType.EXPENSE,
        value = 123,
        description = "desc",
        calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, -1)
        },
    )
}
