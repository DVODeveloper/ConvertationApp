package com.example.convertationapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyInfoDbModel(
    @PrimaryKey
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double
)
