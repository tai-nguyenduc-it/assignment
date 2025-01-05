package com.crypto.currency.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel

@Dao
interface CurrencyDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertCurrency(currency: CurrencyDatabaseModel)

    @Insert(onConflict = REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyDatabaseModel>)

    @Query("SELECT * FROM currency_table")
    suspend fun getAllCurrencies(): List<CurrencyDatabaseModel>

    @Query("SELECT * FROM currency_table WHERE id = :id")
    suspend fun getCurrencyById(id: String): CurrencyDatabaseModel?

    @Delete
    suspend fun deleteCurrency(currency: CurrencyDatabaseModel)

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrencies()
}
