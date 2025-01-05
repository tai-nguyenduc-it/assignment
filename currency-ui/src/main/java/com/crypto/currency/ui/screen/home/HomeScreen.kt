package com.crypto.currency.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.architecture.ui.screen.Screen
import com.crypto.currency.presentation.screen.home.HomeViewModel
import com.crypto.currency.presentation.screen.home.HomeViewState
import com.crypto.currency.ui.R
import com.widget.theme.CryptoDimensions
import com.widget.ui.button.Button
import com.widget.ui.button.ButtonState
import com.widget.ui.button.ButtonState.Enabled
import com.widget.ui.button.ButtonState.Loading

@Composable
fun HomeScreen() = Screen<HomeViewState, HomeViewModel> {
    Content { viewState ->
        val cleanButtonState = remember(viewState.isDeletingCurrencies) {
            if (viewState.isDeletingCurrencies) Loading else Enabled
        }

        val addButtonState = remember(viewState.isAddingCurrencies) {
            if (viewState.isAddingCurrencies) Loading else Enabled
        }

        HomeContent(
            cleanButtonState = cleanButtonState,
            addButtonState = addButtonState,
            onCleanClick = viewModel::onDeleteCurrenciesAction,
            onAddClick = viewModel::onAddCurrenciesAction,
            onCryptoListClick = {},
            onFiatListClick = {},
            onAllCurrencyClick = {}
        )
    }
}

@Composable
private fun HomeContent(
    cleanButtonState: ButtonState,
    addButtonState: ButtonState,
    onCleanClick: () -> Unit,
    onAddClick: () -> Unit,
    onCryptoListClick: () -> Unit,
    onFiatListClick: () -> Unit,
    onAllCurrencyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(CryptoDimensions.space32),
            verticalArrangement = Arrangement.spacedBy(CryptoDimensions.space8)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_assignment_title),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(CryptoDimensions.space32))
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_clean_data_button_title),
                state = cleanButtonState,
                onClick = onCleanClick
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_add_data_button_title),
                state = addButtonState,
                onClick = onAddClick
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_crypto_list_button_title),
                onClick = onCryptoListClick
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_fiat_list_button_title),
                onClick = onFiatListClick
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_currency_list_button_title),
                onClick = onAllCurrencyClick
            )
        }
    }
}
