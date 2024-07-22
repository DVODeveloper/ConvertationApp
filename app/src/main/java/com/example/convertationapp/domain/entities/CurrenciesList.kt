package com.example.convertationapp.domain.entities

data class CurrenciesList(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String, Valute>
)
