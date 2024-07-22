package com.example.convertationapp.data.repositoryImpl

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convertationapp.data.api.ApiService
import com.example.convertationapp.data.mapper.CurrencyMapper
import com.example.convertationapp.domain.domain_repository.DomainRepository
import com.example.convertationapp.domain.entities.Valute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DomainRepositoryImpl(private val apiService: ApiService) : DomainRepository {

    val currencyList: MutableLiveData<List<Valute>> = MutableLiveData()
    val currencyListName: MutableLiveData<List<String>> = MutableLiveData()
    val cost: MutableLiveData<Double> = MutableLiveData()


    override suspend fun getListCurrency(): LiveData<List<Valute>> {


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currenciesList = apiService.getCurrenciesList()
                val currencyValues = currenciesList.Valute.values.toList()

                currencyList.postValue(currencyValues)
            } catch (e: Exception) {
                currencyList.postValue(emptyList())
            }
        }
        return currencyList
    }

    override suspend fun getListForSpinner(): LiveData<List<String>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currenciesList = apiService.getCurrenciesList()
                val currencyValues = currenciesList.Valute.values.toList().map { it.CharCode }

                currencyListName.postValue(currencyValues)
            } catch (e: Exception) {
                currencyListName.postValue(emptyList())
            }
        }
        return currencyListName

    }

    override suspend fun convertationCurrency(
        choosenCharCode: String,
        amount: String
    ): LiveData<Double> {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currenciesList = apiService.getCurrenciesList()
                val currencyValues = currenciesList.Valute.values.toList()

                for (element in currencyValues) {
                    if (element.CharCode == choosenCharCode) {
                        val conversionRate = element.Value.toDouble() / element.Nominal.toDouble()
                        val amountDouble = amount.toDouble()
                        cost.postValue(conversionRate * amountDouble)
                        return@launch
                    }
                }

            } catch (e: Exception) {
                cost.postValue(0.0)
            }
        }
        return cost

    }
}