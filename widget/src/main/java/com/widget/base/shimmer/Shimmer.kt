package com.widget.base.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun rememberShimmer(
    shimmerBounds: ShimmerBounds,
    theme: com.widget.base.shimmer.ShimmerTheme = com.widget.base.shimmer.LocalShimmerTheme.current
): Shimmer {
    val effect = rememberShimmerEffect(theme)
    val bounds = rememberShimmerBounds(shimmerBounds)
    val shimmer = remember(theme, effect) {
        Shimmer(theme, effect, bounds)
    }
    shimmer.updateBounds(bounds)
    return shimmer
}

class Shimmer internal constructor(
    internal val theme: com.widget.base.shimmer.ShimmerTheme,
    internal val effect: ShimmerEffect,
    bounds: Rect?
) {

    internal val boundsFlow = MutableStateFlow(bounds)

    fun updateBounds(bounds: Rect?) {
        boundsFlow.value = bounds
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Shimmer

        if (theme != other.theme) return false
        if (effect != other.effect) return false

        return true
    }

    override fun hashCode(): Int {
        var result = theme.hashCode()
        result = 31 * result + effect.hashCode()
        return result
    }
}
