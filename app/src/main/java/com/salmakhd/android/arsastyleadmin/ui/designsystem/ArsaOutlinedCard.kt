package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArsaOutlinedCard(
    surfaceModifier: Modifier=Modifier,
    colors: CardColors = CardDefaults.outlinedCardColors(),
    content: @Composable () -> Unit = {}
) {
    OutlinedCard(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(15.dp),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.large,
        colors = colors
    ) {
        Surface(modifier = surfaceModifier) {
            content()
        }
    }
}