package com.crypto.currency.datasource.source

import com.crypto.currency.datasource.database.CurrencyDao
import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CurrencySourceTest {
    private val mockDao = mock(CurrencyDao::class.java)
    private val source = CurrencySourceImpl(mockDao)

    @Test
    fun `Given currencies When addCurrencies Then insert into database`() = runBlocking {
        // Given
        val currencies = listOf(
            Crypto(id = "1", name = "Bitcoin", symbol = "BTC"),
            Fiat(id = "2", name = "USD", symbol = "USD", code = "USD")
        )

        // When
        source.addCurrencies(currencies)

        //Then
        verify(mockDao).insertCurrencies(anyList())
    }

    @Test
    fun `search maps database results to data models`() = runBlocking {
        // Given
        val keyword = "Bitcoin"
        val databaseResults = listOf(
            CurrencyDatabaseModel(id = "1", name = "Bitcoin", symbol = "BTC", code = ""),
            CurrencyDatabaseModel(id = "2", name = "US Dollar", symbol = "USD", code = "USD")
        )
        given(mockDao.search(keyword)).willReturn(databaseResults)

        // When
        val result = source.search(keyword)

        // Then
        verify(mockDao).search(keyword)
        assert(result.size == 2)
        assert(result[0] is Crypto)
        assert(result[1] is Fiat)
        assert(result[0].name == "Bitcoin")
        assert(result[1].name == "US Dollar")
    }
}