package com.hdeva.expensr.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hdeva.expensr.R
import com.hdeva.expensr.databinding.BottomSheetAddTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetAddTransactionBinding

    private val viewModel: AddTransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return BottomSheetAddTransactionBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        binding.add.setOnClickListener {
            tryAddTransaction()
        }
    }

    private fun setupObserver() {
        viewModel.state.observe(this) { state ->
            when (state) {
                AddTransactionViewModel.AddTransactionState.EDITING -> handleEditing()
                AddTransactionViewModel.AddTransactionState.ADDING -> handleAdding()
                AddTransactionViewModel.AddTransactionState.SUCCESS -> handleSuccess()
                AddTransactionViewModel.AddTransactionState.FAILURE -> handleFailure()
            }
        }
    }

    private fun handleEditing() {
        binding.add.isEnabled = true
    }

    private fun handleAdding() {
        binding.add.isEnabled = false
    }

    private fun handleSuccess() {
        Toast.makeText(
            requireContext(),
            R.string.transaction_added,
            Toast.LENGTH_SHORT,
        ).show()
        dismiss()
    }

    private fun handleFailure() {
        binding.add.isEnabled = true
        Toast.makeText(
            requireContext(),
            R.string.oops_something_went_wrong,
            Toast.LENGTH_SHORT,
        ).show()
    }

    private fun tryAddTransaction() {
        viewModel.tryAddTransaction(
            binding.typeRadio.checkedRadioButtonId,
            binding.descriptionText.text.toString(),
            binding.valueText.text.toString(),
        )
    }

    companion object {
        const val TAG = "AddTransaction"
        fun newInstance() = AddTransactionBottomSheet()
    }
}
