package com.hdeva.expensr.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.hdeva.expensr.databinding.FragmentDialogAddTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragmentDialog : DialogFragment() {

    private lateinit var binding: FragmentDialogAddTransactionBinding

    private val viewModel: AddTransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDialogAddTransactionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            tryAddTransaction()
        }
    }

    private fun tryAddTransaction() {

    }

    companion object {
        const val TAG = "AddTransaction"
        fun newInstance() = AddTransactionFragmentDialog()
    }
}
