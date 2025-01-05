package com.common.di

import com.common.logger.Logger
import com.common.logger.PlatformLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    fun providesLogger(): Logger = PlatformLogger()
}
