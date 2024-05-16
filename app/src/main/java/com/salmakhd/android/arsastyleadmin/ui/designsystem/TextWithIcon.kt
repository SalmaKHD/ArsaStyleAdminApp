package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.labelMedium,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .clickable {
            onClick()
        }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier,
            text = label,
            style = textStyle,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@SmallDevicePreview
@Composable
fun TextWithIconPreview() {
    ArsaStyleAdminTheme {
        TextWithIcon(label = "fdfd", iconId = R.drawable.ic_dryer) {
        }
    }
}