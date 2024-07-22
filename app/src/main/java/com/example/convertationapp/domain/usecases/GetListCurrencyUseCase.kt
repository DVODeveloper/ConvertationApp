package com.example.convertationapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.convertationapp.domain.domain_repository.DomainRepository
import com.example.convertationapp.domain.entities.Valute

class GetListCurrencyUseCase(private val domainRepository: DomainRepository) {

    suspend fun getListCurrency(): LiveData<List<Valute>> {
        return domainRepository.getListCurrency()
    }
}