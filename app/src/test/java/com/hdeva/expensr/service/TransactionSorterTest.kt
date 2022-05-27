package com.hdeva.expensr.service

import com.hdeva.expensr.TransactionExamples
import com.hdeva.expensr.domain.model.Transaction
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TransactionSorterTest {

    lateinit var sut: TransactionSorter

    @Before
    fun setup() {
        sut = TransactionSorter()
    }

    @Test
    fun `test empty list returns empty result`() {
        val list = listOf<Transaction>()

        val sorted = sut.groupAndSortTransactions(list)

        assert(list == sorted)
    }

    @Test
    fun `test sort and group sorts by descending order and groups by day`() {
        val list = listOf(
            TransactionExamples.YESTERDAY_ITEM,
            TransactionExamples.ONE_MINUTE_AGO_ITEM,
            TransactionExamples.SIMPLE_EXPENSE,
        )

        val groupedAndSorted = sut.groupAndSortTransactions(list)

        assert(groupedAndSorted.size == 2)

        assert(groupedAndSorted[0].size == 2)
        assert(groupedAndSorted[1].size == 1)

        assert(groupedAndSorted[0][0] == TransactionExamples.SIMPLE_EXPENSE)
        assert(groupedAndSorted[0][1] == TransactionExamples.ONE_MINUTE_AGO_ITEM)
        assert(groupedAndSorted[1][0] == TransactionExamples.YESTERDAY_ITEM)
    }
}
