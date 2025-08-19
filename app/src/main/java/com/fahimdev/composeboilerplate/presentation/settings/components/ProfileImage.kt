package com.fahimdev.composeboilerplate.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    firstName: String,
    lastName: String,
    size: Dp
) {
    val initials = "${firstName.firstOrNull()?.uppercaseChar() ?: ""}${
        lastName.firstOrNull()?.uppercaseChar() ?: ""
    }"

    if (imageUrl.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .size(size)
                .background(MaterialTheme.colorScheme.background, CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp
                )
            )
        }
        return
    }

    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile Image",
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    )
}