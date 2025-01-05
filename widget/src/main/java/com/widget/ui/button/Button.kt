package com.widget.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.widget.base.shimmer.shimmerBackground
import com.widget.theme.CryptoColors
import com.widget.theme.CryptoDimensions
import com.widget.ui.button.ButtonState.Disabled
import com.widget.ui.button.ButtonState.Enabled
import com.widget.ui.button.ButtonState.InitialLoading
import com.widget.ui.button.ButtonState.Loading
import com.widget.ui.button.ButtonStyle.Borderless
import com.widget.ui.button.ButtonStyle.Primary
import com.widget.ui.button.ButtonStyle.Secondary
import com.widget.ui.button.ButtonStyle.Tertiary
import androidx.compose.material3.Button as FilledButton

enum class ButtonStyle {
    Primary,
    Secondary,
    Tertiary,
    Borderless
}

enum class ButtonState {
    Enabled,
    Disabled,
    InitialLoading,
    Loading
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String = "",
    state: ButtonState = Enabled,
    style: ButtonStyle = Primary,
    onClick: () -> Unit = {}
) {
    val isEnabled = remember(state) { state == Enabled }
    val isLoading = remember(state) { state == Loading }

    @Composable
    fun Content() {
        Box {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                    color = style.toCircularProgressIndicatorColor(),
                    strokeWidth = 2.dp
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isLoading) CryptoColors.transparent else state.toTextColor(style)
            )
        }
    }

    if (state == InitialLoading) {
        Box(
            modifier = modifier
                .padding(top = CryptoDimensions.space4)
                .widthIn(ButtonDefaults.MinWidth)
                .heightIn(ButtonDefaults.MinHeight)
                .shimmerBackground(RoundedCornerShape(CryptoDimensions.radiusFull))
        )
    } else {
        when (style) {
            Primary -> {
                FilledButton(
                    modifier = modifier,
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = CryptoColors.grey900,
                        disabledContainerColor = if (isLoading) CryptoColors.grey900 else CryptoColors.grey400
                    ),
                    enabled = isEnabled
                ) { Content() }
            }

            Secondary -> {
                FilledButton(
                    modifier = modifier,
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = CryptoColors.teal200,
                        disabledContainerColor = if (isLoading) CryptoColors.teal200 else CryptoColors.grey400
                    ),
                    enabled = isEnabled
                ) { Content() }
            }

            Tertiary -> {
                OutlinedButton(
                    modifier = modifier,
                    onClick = onClick,
                    border = BorderStroke(1.dp, CryptoColors.grey400),
                    enabled = isEnabled
                ) { Content() }
            }

            Borderless -> {
                TextButton(
                    modifier = modifier,
                    onClick = onClick,
                    enabled = isEnabled
                ) { Content() }
            }
        }
    }
}

@Composable
private fun ButtonState.toTextColor(style: ButtonStyle) = when (this) {
    Enabled -> style.toTextColor()
    Disabled -> style.toDisabledTextColor()
    InitialLoading -> CryptoColors.transparent
    Loading -> CryptoColors.transparent
}

@Composable
private fun ButtonStyle.toTextColor() = when (this) {
    Primary -> CryptoColors.white
    Secondary -> CryptoColors.grey900
    Tertiary -> CryptoColors.grey900
    Borderless -> CryptoColors.grey900
}

@Composable
private fun ButtonStyle.toCircularProgressIndicatorColor() = when (this) {
    Primary -> CryptoColors.white
    Secondary -> CryptoColors.grey900
    Tertiary -> CryptoColors.grey900
    Borderless -> CryptoColors.grey900
}

@Composable
private fun ButtonStyle.toDisabledTextColor() = when (this) {
    else -> CryptoColors.grey50
}
