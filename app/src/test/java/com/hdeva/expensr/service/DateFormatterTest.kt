package com.hdeva.expensr.service

import com.hdeva.expensr.TransactionExamples
import com.hdeva.expensr.domain.model.Transaction
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DateFormatterTest {

    lateinit var sut: DateFormatter

    @Before
    fun setup() {
        sut = DateFormatter()
    }

    @Test
    fun `test empty list returns empty string`() {
        val list = listOf<Transaction>()

        val header = sut.formatTransactionHeaderDate(list)

        assert(header.isEmpty())
    }

    @Test
    fun `test non empty list returns header text`() {
        val list = listOf(
            TransactionExamples.SIMPLE_EXPENSE,
        )

        val header = sut.formatTransactionHeaderDate(list)
        println(header)
        assert(header.isNotEmpty())
    }
}
