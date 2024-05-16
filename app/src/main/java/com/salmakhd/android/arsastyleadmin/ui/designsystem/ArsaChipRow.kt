package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ArsaChipRow(
    modifier: Modifier = Modifier,
    tags: List<String>,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(tags, key = {it}) {
            ArsaChip(
                label = it,
                modifier = Modifier.padding(horizontal = 2.dp),
                textStyle = textStyle
            )
        }
    }
}