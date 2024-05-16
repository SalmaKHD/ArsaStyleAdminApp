package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen

@Composable
fun ArsaBasicScreen(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    shouldBeScrollable: Boolean = true,
    shouldHaveHorizontalPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val state = rememberScrollState()
    val backgroundColor = MaterialTheme.colorScheme.surface
    val columnModifier = remember {
        if(shouldBeScrollable) {
            modifier
                .fillMaxSize()
                .verticalScroll(state)
                .background(backgroundColor)
                .padding(
                    top = MaterialTheme.dimen.arsaTopPadding,
                    bottom = MaterialTheme.dimen.arsaBottomPadding,
                    start = if(shouldHaveHorizontalPadding) MaterialTheme.dimen.arsaHorizontalPadding else 0.dp,
                    end = if(shouldHaveHorizontalPadding) MaterialTheme.dimen.arsaHorizontalPadding else 0.dp
                )
        } else {
            modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(
                    top = MaterialTheme.dimen.arsaTopPadding,
                    bottom = MaterialTheme.dimen.arsaBottomPadding,
                    start = if(shouldHaveHorizontalPadding) MaterialTheme.dimen.arsaHorizontalPadding else 0.dp,
                    end = if(shouldHaveHorizontalPadding) MaterialTheme.dimen.arsaHorizontalPadding else 0.dp
                )
        }
    }
    Column(
        modifier = columnModifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        content()
    }
}