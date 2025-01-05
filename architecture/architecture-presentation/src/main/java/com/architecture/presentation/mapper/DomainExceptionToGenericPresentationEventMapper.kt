package com.architecture.presentation.mapper

import com.architecture.presentation.event.GenericEvent.InternalServerError
import com.architecture.presentation.event.GenericEvent.UnknownError
import com.crypto.architecture.domain.exception.DomainException
import com.crypto.architecture.domain.exception.ServerErrorDomainException

fun DomainException.toPresentationEvent() = when (this) {
    is ServerErrorDomainException -> InternalServerError
    else -> UnknownError
}
