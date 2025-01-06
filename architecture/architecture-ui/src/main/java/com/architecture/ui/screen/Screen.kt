package com.architecture.ui.screen

import androidx.compose.runtime.Composable
import com.analytics.AnalyticsEvent
import com.architecture.presentation.BaseViewModel
import com.architecture.presentation.PresentationState

@Composable
inline fun <State : PresentationState, reified ViewModel : BaseViewModel<State>> Screen(
    analyticsEvent: AnalyticsEvent? = null,
    content: @Composable ViewScope<State, ViewModel>.() -> Unit
) {
    createViewModelAndBindViewScope<State, ViewModel>(analyticsEvent, content)
}
