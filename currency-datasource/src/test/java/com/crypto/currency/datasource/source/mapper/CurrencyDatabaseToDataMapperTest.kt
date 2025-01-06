package com.crypto.currency.datasource.source.mapper

import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CurrencyDatabaseToDataMapperTest {
    @Test
    fun `Given CurrencyDatabaseModel When toData() Then returns Crypto`() {
        // Given
        val currencyDatabaseModel = CurrencyDatabaseModel(
            id = "1",
            name = "Bitcoin",
            symbol = "BTC",
            code = ""
        )

        // When
        val result = currencyDatabaseModel.toData()

        // Then
        assert(result is Crypto)
        result as Crypto
        assertEquals("1", result.id)
        assertEquals("Bitcoin", result.name)
        assertEquals("BTC", result.symbol)
    }

    @Test
    fun `Given CurrencyDatabaseModel When toData() Then returns Fiat`() {
        // Given
        val currencyDatabaseModel = CurrencyDatabaseModel(
            id = "2",
            name = "US Dollar",
            symbol = "USD",
            code = "USD"
        )

        // When
        val result = currencyDatabaseModel.toData()

        // Then
        assert(result is Fiat)
        result as Fiat
        assertEquals("2", result.id)
        assertEquals("US Dollar", result.name)
        assertEquals("USD", result.symbol)
        assertEquals("USD", result.code)
    }
}