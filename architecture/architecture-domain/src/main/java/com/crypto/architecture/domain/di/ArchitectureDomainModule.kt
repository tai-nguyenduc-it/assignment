package com.crypto.architecture.domain.di

import com.crypto.architecture.domain.UseCaseExecutor
import com.crypto.architecture.domain.UseCaseExecutorProvider
import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.architecture.domain.coroutine.DispatchersCoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ArchitectureDomainModule {
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        DispatchersCoroutineContextProvider()

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider =
        { coroutineScope -> UseCaseExecutor(coroutineScope) }
}
