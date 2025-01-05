package com.widget.base.shimmer

import androidx.compose.foundation.background
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import com.widget.extension.unClippedBoundsInWindow

fun Modifier.shimmerBackground(shape: Shape = RectangleShape) =
    shimmer().background(Color.LightGray, shape)

fun Modifier.shimmer(
    customShimmer: Shimmer? = null
): Modifier = composed(
    factory = {
        val shimmer = customShimmer ?: rememberShimmer(ShimmerBounds.View)

        val width = with(LocalDensity.current) { shimmer.theme.shimmerWidth.toPx() }
        val area = remember(width, shimmer.theme.rotation) {
            com.widget.base.shimmer.ShimmerArea(width, shimmer.theme.rotation)
        }

        LaunchedEffect(area, shimmer) {
            shimmer.boundsFlow.collect {
                area.updateBounds(it)
            }
        }

        remember(area, shimmer) { ShimmerModifier(area, shimmer.effect) }
    },
    inspectorInfo = debugInspectorInfo {
        name = "shimmer"
        properties["customShimmer"] = customShimmer
    }
)

internal class ShimmerModifier(
    private val area: com.widget.base.shimmer.ShimmerArea,
    private val effect: ShimmerEffect
) : DrawModifier, OnGloballyPositionedModifier {

    override fun ContentDrawScope.draw() {
        with(effect) { draw(area) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        val viewBounds = coordinates.unClippedBoundsInWindow()
        area.viewBounds = viewBounds
    }
}
