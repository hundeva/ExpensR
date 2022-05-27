package com.hdeva.expensr.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.hdeva.expensr.R
import com.hdeva.expensr.databinding.DialogFragmentDeleteTransactionBinding
import com.hdeva.expensr.domain.model.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteTransactionDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentDeleteTransactionBinding

    private val viewModel: DeleteTransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogFragmentDeleteTransactionBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancel.setOnClickListener {
            dismiss()
        }
        binding.delete.setOnClickListener {
            viewModel.deleteTransaction(requireArguments().getInt(TRANSACTION_ID))
            Toast.makeText(
                requireContext(),
                R.string.transaction_deleted,
                Toast.LENGTH_SHORT,
            ).show()
            dismiss()
        }
    }

    companion object {
        const val TAG = "DeleteTransaction"
        private const val TRANSACTION_ID = "transaction_id"

        fun newInstance(transaction: Transaction): DeleteTransactionDialogFragment {
            return DeleteTransactionDialogFragment().apply {
                arguments = bundleOf(TRANSACTION_ID to transaction.id)
            }
        }
    }
}
