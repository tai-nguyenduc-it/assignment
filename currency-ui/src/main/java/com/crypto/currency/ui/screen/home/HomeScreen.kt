package com.crypto.currency.ui.screen.home

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.analytics.event.HomeEvent.CleanData
import com.analytics.event.HomeEvent.InsertData
import com.analytics.event.ScreenEvent.Home
import com.analytics.globalAnalyticsReporter
import com.architecture.presentation.event.GenericEvent.Failed
import com.architecture.presentation.event.GenericEvent.Successful
import com.architecture.ui.screen.Screen
import com.crypto.currency.presentation.screen.home.HomeViewModel
import com.crypto.currency.presentation.screen.home.HomeViewState
import com.crypto.currency.ui.R
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.All
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.Crypto
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.Fiat
import com.widget.theme.CryptoDimensions
import com.widget.ui.button.Button
import com.widget.ui.button.ButtonState
import com.widget.ui.button.ButtonState.Enabled
import com.widget.ui.button.ButtonState.Loading

@Composable
fun HomeScreen(
    onCurrencyListAction: (CurrencyTypeUiModel) -> Unit,
) = Screen<HomeViewState, HomeViewModel>(Home) {
    val context = LocalContext.current

    ObserveViewModelEvents { event ->
        when (event) {
            Successful -> showToast(context, R.string.generic_successful)
            Failed -> showToast(context, R.string.generic_failed)
        }

    }
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
            onCleanClick = {
                viewModel.onDeleteCurrenciesAction()
                globalAnalyticsReporter.logEvent(CleanData)
            },
            onAddClick = {
                viewModel.onAddCurrenciesAction()
                globalAnalyticsReporter.logEvent(InsertData)
            },
            onCryptoListClick = { onCurrencyListAction(Crypto) },
            onFiatListClick = { onCurrencyListAction(Fiat) },
            onAllCurrencyClick = { onCurrencyListAction(All) }
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

private fun showToast(context: Context, stringId: String) {
    Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show()
}
