package com.fahimdev.composeboilerplate.ui.components.loader

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.fahimdev.composeboilerplate.ui.theme.ExtendedColors
import com.fahimdev.composeboilerplate.ui.theme.LightPrimary
import com.fahimdev.composeboilerplate.ui.theme.LightSecondary
import com.fahimdev.composeboilerplate.ui.theme.LightTertiary

@Composable
fun GradientSpinnerLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient_spinner")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )

    Canvas(
        modifier = Modifier
            .size(48.dp)
            .rotate(rotation)
    ) {
        val sweepGradient = Brush.sweepGradient(
            colors = listOf(
                Color.Transparent,
                LightPrimary,
                LightSecondary,
                LightTertiary,
                ExtendedColors.Success,
                LightSecondary,
                Color.Transparent
            )
        )

        drawCircle(
            brush = sweepGradient,
            radius = size.minDimension / 2 - 4.dp.toPx(),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}