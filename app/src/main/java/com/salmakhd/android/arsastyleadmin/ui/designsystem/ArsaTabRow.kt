package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.salmakhd.android.arsastyleadmin.R

data class ArsaTabState(
    val title: String = "",
    @DrawableRes val iconRes: Int = R.drawable.ic_dryer
)

@Composable
fun ArsaTabRow(
    modifier: Modifier = Modifier,
    tabs: List<ArsaTabState> = emptyList(),
    selectedTab: Int = 0,
    onTabChanged: (Int ) -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    TabRow(modifier = modifier,selectedTabIndex = selectedTab,
        containerColor = containerColor,
    ) {
        for(tabState in tabs) {
            Tab(
                selected = selectedTab == tabs.indexOf(tabState),
                onClick = { onTabChanged(tabs.indexOf(tabState)) },
                text = {
                    Text(
                        tabState.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                icon = {
                    Icon(painter = painterResource(id =tabState.iconRes), contentDescription = null)
                }
            )
        }
    }
}