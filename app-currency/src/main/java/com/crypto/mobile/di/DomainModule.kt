package com.crypto.mobile.di

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.AddCurrenciesUseCase
import com.crypto.currency.domain.usecase.AddCurrenciesUseCaseImpl
import com.crypto.currency.domain.usecase.GetCurrenciesUseCase
import com.crypto.currency.domain.usecase.GetCurrenciesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun providesGetCurrencyUseCase(
        repository: CurrencyRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetCurrenciesUseCase = GetCurrenciesUseCaseImpl(repository, coroutineContextProvider)

    @Provides
    fun providesAddCurrenciesUseCase(
        repository: CurrencyRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): AddCurrenciesUseCase = AddCurrenciesUseCaseImpl(repository, coroutineContextProvider)
}
