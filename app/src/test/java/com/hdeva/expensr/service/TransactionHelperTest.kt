package com.hdeva.expensr.service

import android.content.Context
import com.hdeva.expensr.TransactionExamples
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TransactionHelperTest {

    lateinit var sut: TransactionHelper

    @Mock
    lateinit var context: Context

    @Before
    fun setup() {
        sut = TransactionHelper()
    }

    @Test
    fun `test if description empty returns fallback text`() {
        whenever(context.getString(any())).thenReturn("fallback")
        val item = TransactionExamples.EMPTY_DESCRIPTION

        val description = sut.formatTransactionDescription(context, item)

        assert(description == "fallback")
    }

    @Test
    fun `test if there is description it is returned`() {
        val item = TransactionExamples.SIMPLE_EXPENSE

        val description = sut.formatTransactionDescription(context, item)

        assert(description == item.description)
    }

    @Test
    fun `test expense transaction value shows minus sign`() {
        val item = TransactionExamples.SIMPLE_EXPENSE

        val value = sut.formatTransactionValue(item)

        assert(value == "-$123")
    }

    @Test
    fun `test zero transaction value shows zero`() {
        val item = TransactionExamples.ZERO_VALUE_ITEM

        val value = sut.formatTransactionValue(item)

        assert(value == "$0")
    }

    @Test
    fun `test income transaction value shows positive value`() {
        val item = TransactionExamples.SIMPLE_INCOME

        val value = sut.formatTransactionValue(item)

        assert(value == "$123")
    }
}
