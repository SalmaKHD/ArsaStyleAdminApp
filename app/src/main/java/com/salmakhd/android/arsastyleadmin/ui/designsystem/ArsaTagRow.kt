package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.common.model.ArsaTagState

@Composable
fun ArsaTagRow(
    arsaTagGroupState: ArsaTagState,
    labelTextStyle: TextStyle =  MaterialTheme.typography.labelMedium,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = arsaTagGroupState.label + ": ",
            style = labelTextStyle,
            color = MaterialTheme.colorScheme.onPrimary
        )
        ArsaChipRow(
            modifier = Modifier.weight(1f),
            tags = arsaTagGroupState.selectedTags,
            textStyle = MaterialTheme.typography.labelSmall
        )
    }
}