package com.fahimdev.composeboilerplate.ui.components.shimmer

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ShimmerEffect")
    val shimmerTransition by infiniteTransition.animateFloat(
        initialValue = -400f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerEffect"
    )

    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.3f),
        Color.Gray.copy(alpha = 0.5f),
        Color.Gray.copy(alpha = 0.3f)
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(shimmerTransition - 200f, shimmerTransition - 200f),
                    end = Offset(shimmerTransition + 200f, shimmerTransition + 200f)
                )
            )
    )


}