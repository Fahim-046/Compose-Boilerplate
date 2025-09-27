package com.fahimdev.composeboilerplate.ui.components.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieHeaderShimmer() {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        ShimmerEffect(
            modifier = Modifier.size(100.dp, 24.dp),
            shape = RoundedCornerShape(8.dp)
        )
        ShimmerEffect(
            modifier = Modifier.size(40.dp, 24.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }
}