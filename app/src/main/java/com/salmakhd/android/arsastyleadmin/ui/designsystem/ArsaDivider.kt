package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArsaDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier,
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.tertiary
    )
}

@SmallDevicePreview
@Composable
fun ArsaDividerPreview(
    modifier: Modifier = Modifier,
) {
    ArsaDivider()
}
