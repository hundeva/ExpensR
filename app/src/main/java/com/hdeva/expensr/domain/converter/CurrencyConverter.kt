package com.hdeva.expensr.domain.converter

import androidx.room.TypeConverter
import com.hdeva.expensr.domain.model.Currency

class CurrencyConverter {
    @TypeConverter
    fun fromString(string: String): Currency {
        return Currency.fromType(string)
    }

    @TypeConverter
    fun toString(currency: Currency): String {
        return currency.type
    }
}
