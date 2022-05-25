package com.hdeva.expensr.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hdeva.expensr.domain.converter.CalendarConverter
import com.hdeva.expensr.domain.converter.CurrencyConverter
import com.hdeva.expensr.domain.converter.TransactionTypeConverter
import java.util.*

@Entity(tableName = "transaction")
@TypeConverters(
    value = [
        TransactionTypeConverter::class,
        CalendarConverter::class,
        CurrencyConverter::class,
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "type")
    val type: TransactionType,
    @ColumnInfo(name = "value")
    val value: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "calendar")
    val calendar: Calendar,
    @ColumnInfo(name = "currency")
    val currency: Currency,
) {
    val groupIndex
        get() = "${calendar.get(Calendar.YEAR)}" +
                "-${calendar.get(Calendar.MONTH)}" +
                "-${calendar.get(Calendar.DAY_OF_MONTH)}"
}

enum class TransactionType(val type: String) {
    EXPENSE("expense"),
    INCOME("income");

    companion object {
        fun fromType(type: String) = values().firstOrNull { element ->
            element.type == type
        } ?: error("Unrecognized transaction type: $type")
    }
}

enum class Currency(val type: String) {
    EUR("EUR"),
    USD("USD");

    companion object {
        fun fromType(type: String) = values().firstOrNull { element ->
            element.type == type
        } ?: error("Unrecognized currency: $type")
    }
}
