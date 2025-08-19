package com.fahimdev.composeboilerplate.presentation.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageAndAppearance(
    isDarkTheme: Boolean,
    selectedLanguage: Languages,
    selectedAppearance: AppearanceTheme,
    onLanguageSelected: (Languages) -> Unit,
    onAppearanceSelected: (AppearanceTheme) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(), border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.outline
        ), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Language,
                    contentDescription = "Language",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Language & Appearance",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 24.sp)
                )
            }
            Text(
                "Customize your language and visual preferences",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Language", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            LanguageSelector(selectedLanguage = selectedLanguage, onLanguageSelected = {
                onLanguageSelected(it)
            })
            Spacer(modifier = Modifier.height(16.dp))
            Text("Appearance", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            AppearanceSelector(isDarkTheme = isDarkTheme, appearance = selectedAppearance, onAppearanceSelected = {
                onAppearanceSelected(it)
            })
        }
    }
}