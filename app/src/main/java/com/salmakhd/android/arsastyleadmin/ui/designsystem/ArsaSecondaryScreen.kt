package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen

@Composable
fun ArsaSecondaryScreen(
    modifier: Modifier = Modifier,
    screenTitle: String,
    onBackPressed: () -> Unit = {},
    screenInstructions: String? = null,
    content: @Composable ColumnScope.() -> Unit = {},
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                top = MaterialTheme.dimen.arsaTopPadding,
                bottom = MaterialTheme.dimen.arsaBottomPadding,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArsaMiniTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.dimen.arsaTopPadding),
            title = screenTitle,
            onBackPressed = onBackPressed
        )
        content()
    }
}