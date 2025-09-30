package com.fahimdev.composeboilerplate.ui.components.topbar

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.getValue
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
    onTrailingIconClick: () -> Unit = {}
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface,
        label = "TopBarContainer"
    )
    val animatedContentColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSurface,
        label = "TopBarContent"
    )
    val animatedDescriptionColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        label = "TopBarDescription"
    )

    val colors = TopAppBarDefaults.topAppBarColors(
        containerColor = animatedContainerColor,
        titleContentColor = animatedContentColor,
        navigationIconContentColor = animatedContentColor,
        actionIconContentColor = animatedContentColor
    )

    if (isCenterAligned) {
        CenterAlignedTopAppBar(
            colors = colors,
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
                            color = animatedDescriptionColor,
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
            colors = colors,
            title = {
                Column {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if(description != null){
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            color = animatedDescriptionColor,
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