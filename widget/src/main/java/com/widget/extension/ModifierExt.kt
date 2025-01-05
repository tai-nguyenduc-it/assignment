package com.widget.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.applyIf(
    condition: Boolean,
    ifFalse: (Modifier.() -> Modifier)? = null,
    ifTrue: @Composable Modifier.() -> Modifier
) = when {
    condition -> then(ifTrue(Modifier))
    ifFalse != null -> then(ifFalse(Modifier))
    else -> this
}
