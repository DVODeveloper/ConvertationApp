package com.example.convertationapp.data.mapper

import com.example.convertationapp.data.database.CurrencyInfoDbModel
import com.example.convertationapp.domain.entities.Valute

class CurrencyMapper {

    fun mapEntityToDbModel(valute: Valute): CurrencyInfoDbModel {
        return CurrencyInfoDbModel(
            ID = valute.ID,
            NumCode = valute.NumCode,
            CharCode = valute.CharCode,
            Nominal = valute.Nominal,
            Name = valute.Name,
            Value = valute.Value,
            Previous = valute.Previous
        )
    }

    fun mapDbModelToEntity(currencyInfoDbModel: CurrencyInfoDbModel): Valute {
        return Valute(
            ID = currencyInfoDbModel.ID,
            NumCode = currencyInfoDbModel.NumCode,
            CharCode = currencyInfoDbModel.CharCode,
            Nominal = currencyInfoDbModel.Nominal,
            Name = currencyInfoDbModel.Name,
            Value = currencyInfoDbModel.Value,
            Previous = currencyInfoDbModel.Previous
        )
    }

    fun mapListDbModelToListEntity(list: List<CurrencyInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}