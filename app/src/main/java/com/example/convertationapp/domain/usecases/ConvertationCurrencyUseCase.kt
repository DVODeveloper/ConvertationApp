package com.example.convertationapp.domain.usecases

import android.text.Editable
import androidx.lifecycle.LiveData
import com.example.convertationapp.domain.domain_repository.DomainRepository

class ConvertationCurrencyUseCase( private val domainRepository: DomainRepository) {

    suspend fun convertationCurrency(choosenCharCode: String, amount: String): LiveData<Double> {
        return domainRepository.convertationCurrency(choosenCharCode, amount)
    }
}