package com.example.convertationapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM currency_table")
    fun getCurrencyList(): LiveData<List<CurrencyInfoDbModel>>

    @Query("SELECT * FROM currency_table WHERE ID == :id LIMIT 1 ")
    fun getInfoAboutCurrency(id: String): LiveData<CurrencyInfoDbModel>
}