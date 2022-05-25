package com.hdeva.expensr.ui.transaction

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hdeva.expensr.databinding.ItemTransactionBinding
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.extension.layoutInflater
import com.hdeva.expensr.service.TransactionHelper
import com.hdeva.expensr.ui.base.BaseViewHolder
import javax.inject.Inject

class TransactionAdapter @Inject constructor(
    private val transactionHelper: TransactionHelper,
) : ListAdapter<Transaction, TransactionViewHolder>(TransactionDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            ItemTransactionBinding.inflate(
                parent.layoutInflater(),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            description.text =
                transactionHelper.formatTransactionDescription(
                    description.context,
                    item,
                )
            value.text = transactionHelper.formatTransactionValue(item)
        }
    }
}

private class TransactionDiffer : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}

class TransactionViewHolder(binding: ItemTransactionBinding) :
    BaseViewHolder<ItemTransactionBinding>(binding)
