package com.crypto.currency.presentation.screen.splash

import com.architecture.presentation.PresentationState

data class SplashViewState(
    val isCompleted: Boolean = false,
    override val isLoading: Boolean = false,
    override val isError: Boolean = false
) : PresentationState
