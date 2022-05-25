package com.hdeva.expensr.ui.transaction

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hdeva.expensr.databinding.ItemTransactionGroupBinding
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.extension.layoutInflater
import com.hdeva.expensr.service.DateFormatter
import com.hdeva.expensr.ui.base.BaseViewHolder
import javax.inject.Inject
import javax.inject.Provider

class TransactionGroupAdapter @Inject constructor(
    private val adapterProvider: Provider<TransactionAdapter>,
    private val dateFormatter: DateFormatter,
) : ListAdapter<List<Transaction>, TransactionGroupViewHolder>(TransactionGroupDiffer()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionGroupViewHolder {
        return TransactionGroupViewHolder(
            ItemTransactionGroupBinding.inflate(
                parent.layoutInflater(),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionGroupViewHolder, position: Int) {
        val items = getItem(position)
        with(holder.binding) {
            header.text = dateFormatter.formatTransactionHeaderDate(items)
            recyclerView.adapter = adapterProvider.get().apply {
                submitList(items)
            }
        }
    }
}

private class TransactionGroupDiffer : DiffUtil.ItemCallback<List<Transaction>>() {
    override fun areItemsTheSame(oldItem: List<Transaction>, newItem: List<Transaction>): Boolean {
        if (oldItem.size != newItem.size) return false

        return oldItem.zip(newItem).all { (old, new) -> old.id == new.id }
    }

    override fun areContentsTheSame(
        oldItem: List<Transaction>,
        newItem: List<Transaction>,
    ): Boolean {
        if (oldItem.size != newItem.size) return false

        return oldItem.zip(newItem).all { (old, new) -> old == new }
    }

}

class TransactionGroupViewHolder(binding: ItemTransactionGroupBinding) :
    BaseViewHolder<ItemTransactionGroupBinding>(binding) {

    init {
        binding.recyclerView.setHasFixedSize(false)
    }
}
