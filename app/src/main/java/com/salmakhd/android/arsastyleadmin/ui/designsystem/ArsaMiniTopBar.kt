package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@Composable
fun ArsaMiniTopBar(
    modifier: Modifier = Modifier,
    title: String,
    screenInstructions: String? = null,
    onBackPressed: () -> Unit = {}
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically)
    {
        ArsaScreenTitle(title = title, )
        IconButton(
            modifier = Modifier
                .wrapContentSize(),
            onClick = { onBackPressed() }) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}

@SmallDevicePreview
@Composable
fun ArsaMiniTopBarPreview() {
    ArsaStyleAdminTheme {
        ArsaMiniTopBar(title = "پشتیبانی")
    }
}
