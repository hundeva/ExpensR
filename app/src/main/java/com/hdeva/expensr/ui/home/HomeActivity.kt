package com.hdeva.expensr.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.hdeva.expensr.R
import com.hdeva.expensr.databinding.ActivityHomeBinding
import com.hdeva.expensr.ui.base.BaseActivity
import com.hdeva.expensr.ui.transaction.AddTransactionBottomSheet
import com.hdeva.expensr.ui.transaction.TransactionGroupAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun createBinding() = ActivityHomeBinding.inflate(layoutInflater)

    @Inject
    lateinit var groupAdapter: TransactionGroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_randomize -> {
                viewModel.addRandomData()
                true
            }
            R.id.home_clear_data -> {
                viewModel.clearData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupUi() {
        with(binding) {
            recyclerView.setHasFixedSize(false)
            recyclerView.adapter = groupAdapter

            addTransaction.setOnClickListener {
                AddTransactionBottomSheet.newInstance()
                    .show(
                        supportFragmentManager,
                        AddTransactionBottomSheet.TAG,
                    )
            }
        }
    }

    private fun setupObserver() {
        viewModel.allTransactions.observe(this) { transactions ->
            groupAdapter.submitList(viewModel.groupAndSortTransactions(transactions))
        }

        viewModel.allExpenses.observe(this) { expenses ->
            binding.expenseValue.text = expenses?.toString() ?: "-"
        }

        viewModel.allIncome.observe(this) { income ->
            binding.incomeValue.text = income?.toString() ?: "-"
        }

        viewModel.balance.observe(this) { balance ->
            binding.balanceValue.text = balance?.toString() ?: "-"
        }

        viewModel.balanceRatio.observe(this) { balanceRatio ->
            val indicatorColor = ContextCompat.getColor(
                this,
                if (balanceRatio < 100) R.color.green else R.color.red,
            )
            binding.balanceRatio.setIndicatorColor(indicatorColor)
            binding.balanceRatio.setProgress(balanceRatio, true)
        }
    }
}
