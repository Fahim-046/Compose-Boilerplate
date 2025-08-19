package com.fahimdev.composeboilerplate.ui.components.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fahimdev.composeboilerplate.ui.components.loader.BouncingDotsLoader
import com.fahimdev.composeboilerplate.ui.components.loader.BreathingDotsLoader
import com.fahimdev.composeboilerplate.ui.components.loader.GradientSpinnerLoader
import com.fahimdev.composeboilerplate.ui.components.loader.LoaderType
import com.fahimdev.composeboilerplate.ui.components.loader.OrbitLoader
import com.fahimdev.composeboilerplate.ui.components.loader.PulseRingLoader
import com.fahimdev.composeboilerplate.ui.components.loader.WaveLoader

@Composable
fun LoadingDialog(
    isVisible: Boolean = false,
    onDismiss: () -> Unit = {},
    message: String = "Loading",
    loaderType: LoaderType = LoaderType.BOUNCING_DOTS
) {
    if (!isVisible) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(300)) + scaleIn(
                initialScale = 0.8f,
                animationSpec = tween(300)
            ),
            exit = fadeOut(animationSpec = tween(300)) + scaleOut(
                targetScale = 0.8f,
                animationSpec = tween(300)
            )
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (loaderType) {
                        LoaderType.BOUNCING_DOTS -> BouncingDotsLoader()
                        LoaderType.PULSE_RING -> PulseRingLoader()
                        LoaderType.GRADIENT_SPINNER -> GradientSpinnerLoader()
                        LoaderType.WAVE_LOADER -> WaveLoader()
                        LoaderType.ORBIT_LOADER -> OrbitLoader()
                        LoaderType.BREATHING_DOTS -> BreathingDotsLoader()
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        }
    }
}