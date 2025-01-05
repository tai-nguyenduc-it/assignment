package com.architecture.data

import com.architecture.data.mapper.toDomain
import com.architecture.datasource.exception.DataException
import com.crypto.architecture.domain.exception.UnknownDomainException

abstract class BaseRepository {
    protected suspend fun <R> executeSafely(
        exceptionHandler: ((DataException) -> DataException)? = null,
        function: suspend () -> R
    ): R = try {
        function()
    } catch (dataException: DataException) {
        throw if (exceptionHandler == null) {
            (dataException as? DataException)?.toDomain()
                ?: UnknownDomainException(dataException.message.orEmpty())
        } else {
            exceptionHandler(dataException)
        }
    }
}
