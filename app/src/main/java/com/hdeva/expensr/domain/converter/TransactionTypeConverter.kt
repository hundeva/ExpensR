package com.hdeva.expensr.domain.converter

import androidx.room.TypeConverter
import com.hdeva.expensr.domain.model.TransactionType

class TransactionTypeConverter {
    @TypeConverter
    fun fromString(string: String): TransactionType {
        return TransactionType.fromType(string)
    }

    @TypeConverter
    fun toString(transactionType: TransactionType): String {
        return transactionType.type
    }
}
