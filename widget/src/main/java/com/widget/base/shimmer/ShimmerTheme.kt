package com.widget.base.shimmer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ShimmerTheme(

    val animationSpec: AnimationSpec<Float>,
    val blendMode: BlendMode,
    val rotation: Float,
    val shaderColors: List<Color>,
    val shaderColorStops: List<Float>?,
    val shimmerWidth: Dp
)

val defaultShimmerTheme: com.widget.base.shimmer.ShimmerTheme =
    com.widget.base.shimmer.ShimmerTheme(
        animationSpec = infiniteRepeatable(
            animation = com.widget.base.shimmer.shimmerSpec(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 0
            ),
            repeatMode = RepeatMode.Restart
        ),
        blendMode = BlendMode.DstIn,
        rotation = 0f,
        shaderColors = listOf(
            Color.Unspecified.copy(alpha = 1f),
            Color.Unspecified.copy(alpha = 0.25f),
            Color.Unspecified.copy(alpha = 1f)
        ),

        shaderColorStops = listOf(
            0.0f,
            0.5f,
            1.0f
        ),
        shimmerWidth = 200.dp
    )

val LocalShimmerTheme = staticCompositionLocalOf { com.widget.base.shimmer.defaultShimmerTheme }
