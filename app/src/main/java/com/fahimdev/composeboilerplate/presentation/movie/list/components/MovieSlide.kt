package com.fahimdev.composeboilerplate.presentation.movie.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = movie?.coverImage,
            contentDescription = movie?.title,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.f1_movie),
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(
            modifier = modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp, vertical = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(movie?.genres?.size ?: 0) {
                    GenreTag(genre = movie?.genres?.get(it) ?: "")
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
                    text = movie?.rating?.let { ((it * 10).toInt() / 10.0).toString() } ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    Icons.Outlined.CalendarToday,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = "Watch Trailer",
                    icon = Icons.Filled.PlayArrow,
                    isPrimary = true,
                    onClick = { /* Handle trailer click */ }
                )

                ActionButton(
                    text = "More Info",
                    icon = Icons.Filled.Info,
                    isPrimary = false,
                    onClick = { /* Handle more info click */ }
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun MovieSlidePreview() {
    ComposeBoilerplateTheme {
        val dummyMovie = Movie(
            id = 1,
            title = "Movie Title",
            coverImage = "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg", // TMDB poster
            year = 2018,
            rating = 8.0,
            genres = listOf("Action", "Adventure", "Comedy"),
            runtime = 200,
            summary = "It is a movie of someone.",
            imdbCode = "8CV65X4"
        )
        MovieSlide(movie = dummyMovie)
    }
}