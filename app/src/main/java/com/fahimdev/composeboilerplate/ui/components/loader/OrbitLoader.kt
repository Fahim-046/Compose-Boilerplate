package com.fahimdev.composeboilerplate.ui.components.loader

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.fahimdev.composeboilerplate.ui.theme.LightPrimary
import com.fahimdev.composeboilerplate.ui.theme.LightSecondary
import com.fahimdev.composeboilerplate.ui.theme.LightTertiary

@Composable
fun OrbitLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "orbit")

    val rotation1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "orbit1"
    )

    val rotation2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "orbit2"
    )

    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation1)
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.minDimension / 2 - 12.dp.toPx()

            drawCircle(
                color = LightPrimary,
                radius = 4.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(
                    centerX + radius,
                    centerY
                )
            )
        }

        Canvas(
            modifier = Modifier
                .size(32.dp)
                .rotate(rotation2)
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.minDimension / 2 - 8.dp.toPx()

            drawCircle(
                color = LightSecondary,
                radius = 3.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(
                    centerX + radius,
                    centerY
                )
            )
        }

        Box(
            modifier = Modifier
                .size(6.dp)
                .background(LightTertiary, CircleShape)
        )
    }
}