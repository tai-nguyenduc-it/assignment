package com.crypto.currency.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel

@Database(
    entities = [CurrencyDatabaseModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
