package com.crypto.currency.ui.screen.currency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.architecture.ui.screen.Screen
import com.crypto.currency.presentation.screen.currency.CurrencyViewModel
import com.crypto.currency.presentation.screen.currency.CurrencyViewState
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel
import com.crypto.currency.ui.R
import com.crypto.currency.ui.screen.currency.mapper.toUi
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.All
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.Crypto
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel.Fiat
import com.crypto.currency.ui.screen.currency.model.CurrencyUiModel
import com.widget.base.shimmer.shimmerBackground
import com.widget.theme.CryptoColors
import com.widget.theme.CryptoDimensions

private const val NUMBER_OF_LOADING_ITEM = 20

@Composable
fun CurrencyScreen(
    currencyType: CurrencyTypeUiModel,
    onBackAction: () -> Unit
) = Screen<CurrencyViewState, CurrencyViewModel> {
    var searchKeyword by remember(currencyType) { mutableStateOf("") }

    DisposableEffect(searchKeyword) {
        viewModel.onSearchAction(searchKeyword)
        onDispose { viewModel.onClear() }
    }

    Scaffold(
        topBar = {
            TopBar(
                searchKeyword = searchKeyword,
                onSearchChanged = { value -> searchKeyword = value },
                onBackClick = onBackAction
            )
        }
    ) { padding ->
        Content(
            loadingContent = { LoadingState(Modifier.padding(padding)) }
        ) { viewState ->
            val currencies = remember(viewState.currencies) {
                when(currencyType) {
                    Crypto -> viewState.currencies.filterIsInstance<CurrencyPresentationModel.Crypto>()
                    Fiat -> viewState.currencies.filterIsInstance<CurrencyPresentationModel.Fiat>()
                    All -> viewState.currencies
                }.map { currency -> currency.toUi() }
            }
            if (currencies.isEmpty()) {
                EmptyState(Modifier.padding(padding))
            } else {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    currencies.forEach { currency ->
                        CurrencyItem(currency)
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 64.dp),
                            color = CryptoColors.borderFaint
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    searchKeyword: String,
    onSearchChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            SearchInput(
                keyword = searchKeyword,
                onValueChange = onSearchChanged
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun SearchInput(
    keyword: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = keyword,
        onValueChange = onValueChange,
        placeholder = {
            Text(stringResource(R.string.currency_search_hint))
        },
        trailingIcon = {
            if (keyword.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onValueChange("") },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = CryptoColors.transparent,
            unfocusedContainerColor = CryptoColors.transparent,
            focusedIndicatorColor = CryptoColors.transparent,
            unfocusedIndicatorColor = CryptoColors.transparent
        ),
        singleLine = true
    )
}

@Composable
private fun CurrencyItem(
    currency: CurrencyUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CryptoColors.white)
            .padding(CryptoDimensions.space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(CryptoDimensions.dimension32)
                .background(CryptoColors.grey800),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currency.firstLetter,
                style = MaterialTheme.typography.titleMedium,
                color = CryptoColors.white
            )
        }
        Text(
            modifier = Modifier
                .padding(start = CryptoDimensions.space16)
                .weight(1f),
            text = currency.name,
            style = MaterialTheme.typography.titleMedium,
            color = CryptoColors.grey900
        )
        Text(
            modifier = Modifier.padding(start = CryptoDimensions.space16),
            text = currency.symbol,
            style = MaterialTheme.typography.titleMedium,
            color = CryptoColors.grey600
        )
        Icon(
            modifier = Modifier.padding(start = CryptoDimensions.space16),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = CryptoColors.grey600
        )
    }
}

@Composable
private fun EmptyState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = CryptoDimensions.space32)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.img_no_results),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.currency_search_no_result_description),
                style = MaterialTheme.typography.titleMedium,
                color = CryptoColors.grey700
            )
        }
    }
}

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        List(NUMBER_OF_LOADING_ITEM) {
            CurrencyItemLoadingState()
            HorizontalDivider(
                modifier = Modifier.padding(start = 64.dp),
                color = CryptoColors.borderFaint
            )
        }
    }
}

@Composable
private fun CurrencyItemLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .shimmerBackground()
    )
}

