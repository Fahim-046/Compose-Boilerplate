package com.fahimdev.composeboilerplate.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieTileShimmer(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(650.dp)
            .padding(16.dp),
        userScrollEnabled = false
    ) {
        items(4) {
            Card(
                modifier = modifier
                    .width(200.dp)
                    .height(300.dp)
            ) {
                Column {
                    ShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(16.dp),
                            shape = RoundedCornerShape(4.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(16.dp),
                            shape = RoundedCornerShape(4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShimmerEffect(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(12.dp),
                                shape = RoundedCornerShape(4.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Star icon shimmer
                                ShimmerEffect(
                                    modifier = Modifier.size(16.dp),
                                    shape = CircleShape
                                )

                                // Rating text shimmer
                                ShimmerEffect(
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(12.dp),
                                    shape = RoundedCornerShape(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}