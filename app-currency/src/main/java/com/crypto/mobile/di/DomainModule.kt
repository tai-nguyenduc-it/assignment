package com.crypto.mobile.di

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.AddCurrenciesUseCase
import com.crypto.currency.domain.usecase.AddCurrenciesUseCaseImpl
import com.crypto.currency.domain.usecase.DeleteCurrenciesUseCase
import com.crypto.currency.domain.usecase.DeleteCurrenciesUseCaseImpl
import com.crypto.currency.domain.usecase.SearchCurrenciesUseCase
import com.crypto.currency.domain.usecase.SearchCurrenciesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun providesAddCurrenciesUseCase(
        repository: CurrencyRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): AddCurrenciesUseCase = AddCurrenciesUseCaseImpl(repository, coroutineContextProvider)

    @Provides
    fun providesDeleteCurrenciesUseCase(
        repository: CurrencyRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): DeleteCurrenciesUseCase = DeleteCurrenciesUseCaseImpl(repository, coroutineContextProvider)

    @Provides
    fun providesSearchCurrenciesUseCase(
        repository: CurrencyRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SearchCurrenciesUseCase = SearchCurrenciesUseCaseImpl(repository, coroutineContextProvider)
}
