package com.example.convertationapp.domain.domain_repository

import android.text.Editable
import androidx.lifecycle.LiveData
import com.example.convertationapp.domain.entities.Valute

interface DomainRepository {

    suspend fun getListCurrency(): LiveData<List<Valute>>

    suspend fun getListForSpinner(): LiveData<List<String>>

    suspend fun convertationCurrency(choosenCharCode: String, amount: String): LiveData<Double>

}