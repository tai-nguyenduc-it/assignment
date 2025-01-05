package com.architecture.presentation

data class EmptyViewState(
    override val isLoading: Boolean = false,
    override val isError: Boolean = false
) : PresentationState
