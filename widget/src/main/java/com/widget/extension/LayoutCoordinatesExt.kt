package com.widget.extension

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.positionInWindow

/**
 * The regular [boundsInWindow] returns a rect that is clipped by the edges of the window. So if
 * a view is scrolled half way out of the screen, the rect returned would only have half the size
 * required for the shimmer calculations.
 *
 * @return Rect that is not clipped by the window's size.
 */
fun LayoutCoordinates.unClippedBoundsInWindow() = try {
    val positionInWindow = positionInWindow()
    Rect(
        positionInWindow.x,
        positionInWindow.y,
        positionInWindow.x + size.width,
        positionInWindow.y + size.height
    )
} catch (_: IllegalStateException) {
    Rect.Zero
}
