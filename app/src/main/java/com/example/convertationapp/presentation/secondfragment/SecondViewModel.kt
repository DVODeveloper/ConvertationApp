package com.example.convertationapp.presentation.secondfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convertationapp.data.api.RetrofitInstance
import com.example.convertationapp.data.repositoryImpl.DomainRepositoryImpl
import com.example.convertationapp.domain.usecases.GetListCurrencyUseCase
import kotlinx.coroutines.launch

class SecondViewModel() : ViewModel() {


    private val repository = DomainRepositoryImpl(RetrofitInstance.apiService)
    private val getListCurrencyUseCase = GetListCurrencyUseCase(repository)

    val listOfCurrency = repository.currencyList

    fun getList() {
        viewModelScope.launch {
            val currencies = getListCurrencyUseCase.getListCurrency().value
            listOfCurrency.postValue(currencies ?: emptyList())
        }
    }
}