package com.architecture.data.mapper

import com.architecture.datasource.exception.DataException
import com.architecture.datasource.exception.ServerErrorDataException
import com.crypto.architecture.domain.exception.DomainException
import com.crypto.architecture.domain.exception.ServerErrorDomainException
import com.crypto.architecture.domain.exception.UnknownDomainException

fun DataException.toDomain(): DomainException = when (this) {
    is ServerErrorDataException -> ServerErrorDomainException(message, cause)
    else -> UnknownDomainException(message ?: "No message provided", cause = cause)
}
