package com.fahimdev.composeboilerplate.ui.components.loader

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme

@Composable
private fun LoaderDialogPreview() {
    ComposeBoilerplateTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Bouncing Dot Loader")
                BouncingDotsLoader()
                Text(text = "Breathing Dot Loader")
                BreathingDotsLoader()
                Text(text = "Gradient Spinner Loader")
                GradientSpinnerLoader()
                Text(text = "Orbit Loader")
                OrbitLoader()
                Text(text = "Pulse Ring Loader")
                PulseRingLoader()
                Text(text = "Wave Loader")
                WaveLoader()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoaderDialogPreviewLight() {
    LoaderDialogPreview()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoaderDialogPreviewDark() {
    LoaderDialogPreview()
}