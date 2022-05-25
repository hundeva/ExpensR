package com.hdeva.expensr.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.hdeva.expensr.databinding.ActivityHomeBinding
import com.hdeva.expensr.ui.base.BaseActivity
import com.hdeva.expensr.ui.transaction.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun createBinding() = ActivityHomeBinding.inflate(layoutInflater)

    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        with(binding) {
            recyclerView.setHasFixedSize(false)
            recyclerView.adapter = transactionAdapter

            addTransaction.setOnClickListener {
                viewModel.addRandomData()
            }
        }
    }

    private fun setupObserver() {
        viewModel.allTransactions.observe(this) { transactions ->
            transactionAdapter.submitList(transactions)
        }
    }
}
