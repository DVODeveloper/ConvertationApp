package com.example.convertationapp.presentation.firstfragment

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convertationapp.data.api.RetrofitInstance
import com.example.convertationapp.data.repositoryImpl.DomainRepositoryImpl
import com.example.convertationapp.domain.usecases.ConvertationCurrencyUseCase
import com.example.convertationapp.domain.usecases.GetListForSpinnerUseCase
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel() {

    private val repository = DomainRepositoryImpl(RetrofitInstance.apiService)
    private val getListForSpinnerUseCase = GetListForSpinnerUseCase(repository)
    private val convertationCurrencyUseCase = ConvertationCurrencyUseCase(repository)



    val listForSpinner = repository.currencyListName
    val convertationResult: MutableLiveData<Double> get() = repository.cost

    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String>
        get() = _selectedItem

    fun getListCountriesForSpinner() {
        viewModelScope.launch {
            val currencies = getListForSpinnerUseCase.getListForSpinner().value
            listForSpinner.postValue(currencies ?: emptyList())
        }
    }

    fun convertationCurrency(choosenCharCode: String, amount: String) {
        viewModelScope.launch {
            val result =
                convertationCurrencyUseCase.convertationCurrency(choosenCharCode, amount).value
            convertationResult.postValue(result)
        }
    }

    fun setSelectedItem(item: String) {
        _selectedItem.value = item
    }
}