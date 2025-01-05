package com.crypto.currency.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyDatabaseModel(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String
)
