package com.crypto.currency.datasource.source.mapper

import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CurrencyDataToDatabaseMapperTest {
    @Test
    fun `Given Crypto When toDatabase() Then returns CurrencyDatabaseModel with empty code`() {
        // Given
        val crypto = Crypto(
            id = "1",
            name = "Bitcoin",
            symbol = "BTC"
        )

        // When
        val result = crypto.toDatabase()

        // Then
        assertEquals("1", result.id)
        assertEquals("Bitcoin", result.name)
        assertEquals("BTC", result.symbol)
        assertEquals("", result.code)
    }

    @Test
    fun `Given Fiat When toDatabase() Then returns CurrencyDatabaseModel with code`() {
        // Given
        val fiat = Fiat(
            id = "2",
            name = "US Dollar",
            symbol = "USD",
            code = "USD"
        )

        // When
        val result = fiat.toDatabase()

        // Then
        assertEquals("2", result.id)
        assertEquals("US Dollar", result.name)
        assertEquals("USD", result.symbol)
        assertEquals("USD", result.code)
    }
}