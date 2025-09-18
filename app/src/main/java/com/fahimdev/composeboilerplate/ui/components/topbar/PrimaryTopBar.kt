package com.fahimdev.composeboilerplate.ui.components.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTopBar(
    leadingIcon: ImageVector? = null,
    onLeadingIconClick: () -> Unit = {},
    title: String,
    description: String? = null,
    isCenterAligned: Boolean = false,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: () -> Unit = {},
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surface,
    contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    val colors = if (isCenterAligned) {
        TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        )
    } else {
        TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        )
    }

    if (isCenterAligned) {
        CenterAlignedTopAppBar(
            title = {
                Column {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = contentColor.copy(alpha = 0.7f), // Use contentColor parameter consistently
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            },
            navigationIcon = {
                leadingIcon?.let {
                    IconButton(onClick = onLeadingIconClick) {
                        Icon(
                            imageVector = it,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            },
            actions = {
                trailingIcon?.let {
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(
                            imageVector = it,
                            contentDescription = "More options"
                        )
                    }
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = contentColor.copy(alpha = 0.7f), // Use contentColor parameter consistently
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            },
            navigationIcon = {
                leadingIcon?.let {
                    IconButton(onClick = onLeadingIconClick) {
                        Icon(
                            imageVector = it,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            },
            actions = {
                trailingIcon?.let {
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(
                            imageVector = it,
                            contentDescription = "More options"
                        )
                    }
                }
            }
        )
    }
}