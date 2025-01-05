package com.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architecture.presentation.event.PresentationEvent
import com.architecture.presentation.mapper.toPresentationEvent
import com.crypto.architecture.domain.UseCaseExecutor
import com.crypto.architecture.domain.UseCaseExecutorProvider
import com.crypto.architecture.domain.exception.DomainException
import com.crypto.architecture.domain.usecase.BaseUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<VIEW_STATE : PresentationState>(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    initialState: VIEW_STATE
) : ViewModel() {
    private val useCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(viewModelScope)
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _events = Channel<PresentationEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    protected fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) {
        _state.update { viewState ->
            updatedState(viewState)
        }
    }

    protected fun sendEvent(event: PresentationEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    protected fun notifyError(exception: DomainException) {
        sendEvent(exception.toPresentationEvent())
    }

    open fun onEnter() {}
    open fun onClear() {}

    fun <RESULT> BaseUseCase<Unit, RESULT>.start(
        onResult: (RESULT) -> Unit = {},
        onException: (DomainException) -> Unit = ::notifyError
    ) = useCaseExecutor.execute(this, onResult, onException)

    fun <REQUEST, RESULT> BaseUseCase<REQUEST, RESULT>.start(
        value: REQUEST,
        onResult: (RESULT) -> Unit = {},
        onException: (DomainException) -> Unit = ::notifyError
    ) = useCaseExecutor.execute(this, value, onResult, onException)
}
