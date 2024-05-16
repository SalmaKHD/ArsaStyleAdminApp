package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@Composable
fun ArsaChip (
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = false,
    onItemRemoved: (() -> Unit)? = null,
    onItemClicked: () -> Unit = {},
    selected:Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall
) {
    AssistChip(
        modifier = modifier,
        onClick = {onItemClicked()},
        label = {
            Text(
                overflow = TextOverflow.Ellipsis,
                text = label,
                style = textStyle,
                color = MaterialTheme.colorScheme.onPrimary
            ) },
        enabled = enabled,
        trailingIcon =
        {
            if(onItemRemoved != null) {
                IconButton(onClick = {onItemRemoved()}) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else{ null }
        },
        shape = MaterialTheme.shapes.large,
        border = AssistChipDefaults.assistChipBorder(
            borderColor = if(selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
            disabledBorderColor = MaterialTheme.colorScheme.primary
        ),
        colors = AssistChipDefaults.assistChipColors(
            containerColor =
            if(selected)
                MaterialTheme.colorScheme.tertiary
            else
            {Color.Transparent}
        )
    )
}

@SmallDevicePreview
@Composable
fun ArsaChipPreview() {
    ArsaStyleAdminTheme {
        ArsaChip(label = "fdfd", onItemRemoved = null, enabled = true, selected = false)
    }
}