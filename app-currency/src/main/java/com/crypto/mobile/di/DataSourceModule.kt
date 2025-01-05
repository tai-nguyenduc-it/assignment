package com.crypto.mobile.di

import android.content.Context
import androidx.room.Room
import com.crypto.currency.datasource.database.AppDatabase
import com.crypto.currency.datasource.database.CurrencyDao
import com.crypto.currency.datasource.source.CurrencySource
import com.crypto.currency.datasource.source.CurrencySourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providesCurrencyInfoDao(
        database: AppDatabase
    ): CurrencyDao = database.currencyDao()

    @Provides
    fun providesCurrencySource(
        currencyDao: CurrencyDao
    ): CurrencySource = CurrencySourceImpl(currencyDao)
}