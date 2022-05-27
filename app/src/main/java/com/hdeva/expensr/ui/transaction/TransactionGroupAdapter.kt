package com.hdeva.expensr.ui.transaction

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hdeva.expensr.databinding.ItemTransactionGroupBinding
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.extension.layoutInflater
import com.hdeva.expensr.service.DateFormatter
import com.hdeva.expensr.ui.base.BaseViewHolder
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class TransactionGroupAdapter @Inject constructor(
    private val adapterProvider: Provider<TransactionAdapter>,
    private val dateFormatter: DateFormatter,
    @Named(TransactionModule.TRANSACTION_RECYCLED_VIEW_POOL) private val recycledViewPool: RecyclerView.RecycledViewPool,
) : ListAdapter<List<Transaction>, TransactionGroupViewHolder>(TransactionGroupDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionGroupViewHolder {
        return TransactionGroupViewHolder(
            ItemTransactionGroupBinding.inflate(
                parent.layoutInflater(),
                parent,
                false,
            ).apply {
                recyclerView.setRecycledViewPool(recycledViewPool)
            }
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
