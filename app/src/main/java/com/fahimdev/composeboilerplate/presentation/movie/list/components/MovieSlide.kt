package com.fahimdev.composeboilerplate.presentation.movie.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fahimdev.composeboilerplate.R
import com.fahimdev.composeboilerplate.ui.theme.ComposeBoilerplateTheme
import com.fahimdev.domain.entities.Movie

@Composable
fun MovieSlide(modifier: Modifier = Modifier, movie: Movie?) {
    val gradientBrush = remember {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Black.copy(alpha = 0.3f),
                Color.Black.copy(alpha = 0.8f)
            )
        )
    }

    val ratingText = remember(movie?.rating) {
        movie?.rating?.let { "%.1f".format(it) } ?: ""
    }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = movie?.coverImage,
            contentDescription = movie?.title,
            contentScale = ContentScale.FillBounds,
            clipToBounds = true,
            placeholder = painterResource(id = R.drawable.f1_movie),
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
        )

        Column(
            modifier = modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp, vertical = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                movie?.genres?.take(3)?.forEach { genre ->
                    GenreTag(genre = genre)
                }
            }
            Text(
                text = movie?.title ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = ratingText,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    Icons.Outlined.CalendarMonth,
                    contentDescription = "Release Date",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movie?.year?.toString() ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }
            Text(
                text = movie?.summary ?: "",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}