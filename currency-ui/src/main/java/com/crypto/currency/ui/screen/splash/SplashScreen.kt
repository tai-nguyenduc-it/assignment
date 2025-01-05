package com.crypto.currency.ui.screen.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.architecture.ui.screen.Screen
import com.crypto.currency.presentation.screen.splash.SplashViewModel
import com.crypto.currency.presentation.screen.splash.SplashViewState
import com.crypto.currency.ui.R

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) = Screen<SplashViewState, SplashViewModel> {
    val infiniteTransition = rememberInfiniteTransition("logo-animation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    Content { viewState ->
        LaunchedEffect(viewState.isCompleted) {
            if (viewState.isCompleted) onFinished()
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(160.dp)
                    .graphicsLayer {
                        rotationY = rotation
                    },
                painter = painterResource(R.drawable.img_crypto),
                contentDescription = "crypto image"
            )
        }
    }
}
