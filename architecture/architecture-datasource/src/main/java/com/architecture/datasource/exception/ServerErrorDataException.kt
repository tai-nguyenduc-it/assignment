package com.architecture.datasource.exception

data class ServerErrorDataException(
    override val message: String,
    override val cause: Throwable
) : DataException(message, cause)
