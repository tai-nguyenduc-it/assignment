package com.crypto.mobile.di

import com.crypto.currency.data.repository.CurrencyRepositoryImpl
import com.crypto.currency.datasource.source.CurrencySource
import com.crypto.currency.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun providesCurrencyRepository(
        currencySource: CurrencySource
    ): CurrencyRepository = CurrencyRepositoryImpl(currencySource)
}
