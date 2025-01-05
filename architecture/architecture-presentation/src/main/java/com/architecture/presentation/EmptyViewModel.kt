package com.architecture.presentation

import com.crypto.architecture.domain.UseCaseExecutorProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmptyViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<EmptyViewState>(
    useCaseExecutorProvider,
    EmptyViewState()
)
