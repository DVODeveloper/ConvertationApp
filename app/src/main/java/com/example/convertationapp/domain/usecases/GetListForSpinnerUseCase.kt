package com.example.convertationapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.convertationapp.domain.domain_repository.DomainRepository
import com.example.convertationapp.domain.entities.CurrencyName
import com.example.convertationapp.domain.entities.Valute

class GetListForSpinnerUseCase (private val domainRepository: DomainRepository) {

    suspend fun getListForSpinner(): LiveData<List<String>> {
        return domainRepository.getListForSpinner()
    }
}