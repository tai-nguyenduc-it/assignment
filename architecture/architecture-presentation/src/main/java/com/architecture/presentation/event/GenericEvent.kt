package com.architecture.presentation.event

sealed interface GenericEvent : PresentationEvent {
    data object Successful : GenericEvent
    data object Failed : GenericEvent
    data object InternalServerError : GenericEvent
    data object NoInternetError : GenericEvent
    data object UnknownError : GenericEvent
}
