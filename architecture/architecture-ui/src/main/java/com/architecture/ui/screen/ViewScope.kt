package com.architecture.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.analytics.AnalyticsEvent
import com.analytics.globalAnalyticsReporter
import com.architecture.presentation.BaseViewModel
import com.architecture.presentation.PresentationState
import com.architecture.presentation.event.GenericEvent.InternalServerError
import com.architecture.presentation.event.GenericEvent.NoInternetError
import com.architecture.presentation.event.GenericEvent.UnknownError
import com.architecture.presentation.event.PresentationEvent
import com.architecture.ui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Stable
class ViewScope<State : PresentationState, ViewModel : BaseViewModel<State>>(
    val viewModel: ViewModel
) {
    @Composable
    fun ObserveViewModelEvents(onEvent: (event: PresentationEvent) -> Unit) {
        val context = LocalContext.current
        ObserveEvents(flow = viewModel.events) { event ->
            when (event) {
                InternalServerError -> showToast(context, R.string.internal_server_error)
                NoInternetError -> showToast(context, R.string.no_internet_error)
                UnknownError -> showToast(context, R.string.unknown_error)
                else -> onEvent(event)
            }
        }
    }

    @Composable
    fun Content(
        errorContent: @Composable (State) -> Unit = {},
        loadingContent: @Composable (State) -> Unit = {},
        content: @Composable (State) -> Unit
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        when {
            state.isLoading -> loadingContent(state)
            state.isError -> errorContent(state)
            else -> content(state)
        }
    }

    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    fun showToast(context: Context, messageId: Int, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, messageId, duration).show()
    }
}

@Composable
inline fun <State : PresentationState, reified ViewModel : BaseViewModel<State>> createViewModelAndBindViewScope(
    analyticsEvent: AnalyticsEvent? = null,
    content: @Composable ViewScope<State, ViewModel>.() -> Unit
) {
    val viewmodel: ViewModel = hiltViewModel()
    val scope = remember(viewmodel) { ViewScope(viewmodel) }

    with(scope) {
        LaunchedEffect(key1 = true) {
            viewmodel.onEnter()
            if(analyticsEvent != null) {
                globalAnalyticsReporter.logScreen(analyticsEvent)
            }
        }
        content()
    }
}

@Composable
fun <T> ObserveEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect { event -> onEvent(event) }
            }
        }
    }
}
