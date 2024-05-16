package com.salmakhd.android.arsastyleadmin.screen.routes

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.server.responseModel.UserModel
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDivider
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDropDownMenu
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDropDownMenuState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaHorizontalPagerCandleGraph
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaInfiniteLottieAnimation
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabRow
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.TextWithIcon
import com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields.ArsaSearchField
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen
import com.salmakhd.android.arsastyleadmin.ui.theme.filtersLabel
import com.salmakhd.android.arsastyleadmin.ui.theme.list
import com.salmakhd.android.arsastyleadmin.ui.theme.map

@Composable
fun ActiveUsersScreen() {
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    var isFiltersCardVisible by remember {
        mutableStateOf(false)
    }
    if (isFiltersCardVisible) {
    }
    var suggestionsBoxOffset by remember {
        mutableFloatStateOf(0f)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    TextWithIcon(
                        label = filtersLabel,
                        iconId = R.drawable.ic_filter
                    ) {
                        isFiltersCardVisible = true
                    }
                }
            }

            SalonSearchField(
                value = "",
                onItemClicked = {},
                onFieldValueChanged = {},
                clearButtonVisible = false,
                onClearButtonClicked = { },
                modifier = Modifier
                    .weight(3f)
                    .padding(MaterialTheme.dimen.arsaPadding1)
                    .onGloballyPositioned {
                        suggestionsBoxOffset = it.size.height + 2.dp.value
                    },
            )
        }

        ArsaDivider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))
        ArsaTabRow(
            tabs = listOf(
                ArsaTabState(
                    title = list,
                    iconRes = R.drawable.ic_list
                ),
                ArsaTabState(
                    title = map,
                    iconRes = R.drawable.ic_map
                ),
                ArsaTabState(
                    title = "آمار",
                    iconRes = R.drawable.ic_statistics
                )
            ),
            containerColor = Color.Transparent,
            selectedTab = selectedTab,
            onTabChanged = { selectedTab = it }
        )
        Spacer(modifier = Modifier.height(5.dp))

        AnimatedContent(targetState = selectedTab) { selectedTab ->
            when(selectedTab) {
                0 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        UserCard(
                            user =
                            UserModel()
                        )
                    }
                }

                1 -> {
                    Box(modifier = Modifier.fillMaxSize())
                }

                else -> {
                    Box(modifier = Modifier
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        ArsaHorizontalPagerCandleGraph(
                            modifier = Modifier.fillMaxSize(),
                            tabs = listOf(
                                ArsaTabState(
                                    title = "کاربران فعال",
                                    iconRes = R.drawable.ic_identity
                                ),
                            ),
                            statisticsList = listOf(
                                listOf(
                                    1, 2, 3, 4, 5, 6, 7, 6, 5, 41, 2
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserList(
    users: List<UserModel> = emptyList(),
    modifier: Modifier = Modifier
) {

}

@Composable
fun UserCard(
    user: UserModel,
    modifier: Modifier = Modifier
) {
    VerificationCard<Int>(modifier = modifier
        .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ArsaInfoRow(title = "شناسه کاربر: ", info = user.username)
            ArsaInfoRow(title = "نام کاربر: ", info = "${user.firstname} ${user.lastname}")
            ArsaInfoRow(title = "نقش: " , info =
            when(user.side) {
                "Customer" -> "مشتری"
                else -> "آرایشگر"
            },)
            ArsaInfoRow(title = "استان:", info =user.province)
            ArsaInfoRow(title = "تاریخ پیوستن: ", info = user.adDate
            )
        }
    }
}


@Composable
fun SalonSearchField(
    value: String?,
    modifier: Modifier = Modifier,
    onFieldValueChanged: (String) -> Unit = {},
    suggestedUsers: List<UserModel> = emptyList(), // expected format: name, address
    onItemClicked: (UserModel) -> Unit = {},
    clearButtonVisible: Boolean = false,
    onClearButtonClicked: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ArsaDropDownMenu(
            modifier = Modifier.fillMaxWidth(),
            selectedItemBox = {
                ArsaSearchField(
                    value = value,
                    onFieldValueChanged = { onFieldValueChanged(it); },
                    clearButtonVisible = clearButtonVisible,
                    onClearButtonClicked = onClearButtonClicked
                )
            },
            menuItems =
            suggestedUsers.map {
                ArsaDropDownMenuState(
                    identifier = it, text = "${it.username}, ${it.address}"
                )
            },
            onItemSelected = { onItemClicked(it) },
            menuExpanded = suggestedUsers.isNotEmpty(),
        )
    }
}

@Composable
fun NoItemsFoundBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ArsaInfiniteLottieAnimation(animationRes = R.raw.anim_nothing_found, modifier = Modifier.fillMaxWidth(0.9f))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "موردی یافت نشد",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}