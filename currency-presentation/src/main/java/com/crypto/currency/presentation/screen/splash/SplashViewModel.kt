package com.crypto.currency.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.architecture.presentation.BaseViewModel
import com.crypto.architecture.domain.UseCaseExecutorProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

private const val SPLASH_DELAY_IN_MILLISECONDS = 1500_000L
private const val SECONDS_IN_MILLISECOND = 1000L

@HiltViewModel
class SplashViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<SplashViewState>(
    useCaseExecutorProvider,
    SplashViewState()
) {
    private val startTime = System.currentTimeMillis()
    override fun onEnter() {
        onCompleted()
    }

    private fun onCompleted() {
        val executedTime = System.currentTimeMillis() - startTime
        viewModelScope.launch {
            delay(
                max(0, SPLASH_DELAY_IN_MILLISECONDS - executedTime) / SECONDS_IN_MILLISECOND
            )
            updateState { lastState -> lastState.copy(isCompleted = true) }
        }
    }
}
