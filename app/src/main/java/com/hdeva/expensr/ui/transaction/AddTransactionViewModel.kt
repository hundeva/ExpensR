package com.hdeva.expensr.ui.transaction

import androidx.lifecycle.*
import com.hdeva.expensr.R
import com.hdeva.expensr.domain.dao.TransactionDao
import com.hdeva.expensr.domain.model.Transaction
import com.hdeva.expensr.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val dao: TransactionDao,
) : ViewModel() {

    private val _state = MutableLiveData(AddTransactionState.EDITING)
    val state: LiveData<AddTransactionState> = _state.distinctUntilChanged()

    fun tryAddTransaction(typeId: Int, descriptionString: String, valueString: String) {
        _state.postValue(AddTransactionState.ADDING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transaction = Transaction(
                    type = when (typeId) {
                        R.id.type_expense -> TransactionType.EXPENSE
                        R.id.type_income -> TransactionType.INCOME
                        else -> error("No type selected")
                    },
                    value = valueString.toInt(),
                    description = descriptionString,
                    calendar = Calendar.getInstance(),
                )
                dao.insertTransaction(transaction)
                _state.postValue(AddTransactionState.SUCCESS)
            } catch (throwable: Throwable) {
                _state.postValue(AddTransactionState.FAILURE)
            }
        }
    }

    enum class AddTransactionState {
        EDITING,
        ADDING,
        SUCCESS,
        FAILURE,
    }
}
