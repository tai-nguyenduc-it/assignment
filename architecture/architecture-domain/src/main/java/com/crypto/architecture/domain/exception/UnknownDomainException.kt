package com.crypto.architecture.domain.exception

data class UnknownDomainException(
    override val message: String = "",
    override val cause: Throwable? = null
) : DomainException(message, cause ?: RuntimeException(message))
