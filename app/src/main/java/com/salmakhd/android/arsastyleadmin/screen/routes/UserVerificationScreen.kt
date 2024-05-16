package com.salmakhd.android.arsastyleadmin.screen.routes

import android.content.Intent
import android.location.Location
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.common.model.ArsaTagState
import com.salmakhd.android.arsastyleadmin.common.model.SalonService
import com.salmakhd.android.arsastyleadmin.common.model.ServiceRating
import com.salmakhd.android.arsastyleadmin.screen.navigation.ArsaMenu
import com.salmakhd.android.arsastyleadmin.screen.viewmodels.UserVerificationViewModel
import com.salmakhd.android.arsastyleadmin.server.model.SalonCommentDTO
import com.salmakhd.android.arsastyleadmin.server.model.StylistDTO
import com.salmakhd.android.arsastyleadmin.server.responseModel.UserModel
import com.salmakhd.android.arsastyleadmin.ui.designsystem.AllSizeDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaBasicScreen
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaButton
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDropDownMenuState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaNetworkScreen
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaOutlinedCard
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaRatingBar
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabRow
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTagRow
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@AllSizeDevicePreview
@Composable
fun UserVerificationScreen(
    viewModel: UserVerificationViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    ArsaStyleAdminTheme {
        ArsaBasicScreen(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            shouldBeScrollable = false,
            shouldHaveHorizontalPadding = false
        ) {
            ArsaTabRow(
                tabs = listOf(
                    ArsaTabState(
                        title = "نظرات",
                        iconRes = R.drawable.ic_commnet
                    ),
                    ArsaTabState(
                        title = "هویت",
                        iconRes = R.drawable.ic_identity
                    ),
                    ArsaTabState(
                        title = "خدمات",
                        iconRes = R.drawable.ic_services
                    ),
                ),
                onTabChanged = { selectedTabIndex = it },
                selectedTab = selectedTabIndex,
                containerColor = Color.Transparent
            )
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedContent(targetState = selectedTabIndex) {
                when (it) {
                    0 -> {
                        ArsaNetworkScreen(networkState = viewModel.commentsTabState.networkState) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            ) {
                                items(viewModel.commentsTabState.data, key = { it.id }) {
                                    FullCommentCard(
                                        salonComment = it
                                    )
                                }
                            }
                        }
                    }

                    1 -> {
                        ArsaNetworkScreen(networkState = viewModel.stylistsTabState.networkState) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            ) {
                                items(viewModel.stylistsTabState.data, key = { it.id }) {
                                    SalonVerificationCard(
                                        salonOwnerUser = it,
                                        onFindInMapButtonClicked = {location ->
                                            val uri = Uri.parse("geo:${location.latitude},${location.longitude}")
                                            val intent = Intent(Intent.ACTION_VIEW, uri)
                                            context.startActivity(intent)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    else -> {
                        ArsaNetworkScreen(networkState = viewModel.servicesTabState.networkState) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            ) {
                                items(viewModel.servicesTabState.data, key = { it.id }) {
                                    ServiceVerificationCard(
                                        salonService =
                                        SalonService(
                                            id = 1,
                                            salonId = "nooshin12",
                                            name = "لاک ناخن",
                                            category = "nail",
                                            price = "120.000",
                                            minTime = 12,
                                            maxTime = 30
                                        )
                                    )
                                }
                            }
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun <T> VerificationCard(
    modifier: Modifier = Modifier,
    menuItems: List<ArsaDropDownMenuState<T>> = emptyList(),
    onItemSelected: (T) -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    ArsaOutlinedCard(surfaceModifier = modifier) {
        Column {
            if(menuItems.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    ArsaMenu(
                        menuItems = menuItems,
                        onItemSelected = onItemSelected
                    )
                }
            }
            content()
        }
    }
}

@Composable
fun ServiceVerificationCard(
    salonService: SalonService,
    onSendMessageButtonClicked: (customerId: String) -> Unit = {},
    onVerifyButtonClicked: () -> Unit = {},
    onDenyButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    VerificationCard(modifier = modifier
        .fillMaxWidth(),
        menuItems = listOf(
            ArsaDropDownMenuState(0, "ارسال پیام به مشتری")
        ),
        onItemSelected = {
            when (it) {
                0 -> onSendMessageButtonClicked(salonService.salonId)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ArsaInfoRow(title = "آیدی آرایشگاه: ", info = salonService.salonId)
            ArsaInfoRow(title = "نام سرویس: ", info = salonService.name)
            ArsaInfoRow(title = "دسته بندی: ", info = salonService.category)
            ArsaInfoRow(title = "قیمت:", info = salonService.price)
            ArsaInfoRow(title = "مقدار زمان:", info = "${salonService.minTime}-${salonService.maxTime}")
            Spacer(modifier = Modifier.height(20.dp))
            VerifyOrDenyButtonRow(
                onVerifyButtonClicked = onVerifyButtonClicked,
                onDenyButtonClicked = onDenyButtonClicked
            )
        }
    }
}

@Composable
fun SalonVerificationCard(
    salonOwnerUser: StylistDTO,
    onSendMessageButtonClicked: (customerId: String) -> Unit = {},
    onVerifyButtonClicked: () -> Unit = {},
    onDenyButtonClicked: () -> Unit = {},
    onViewImageClicked: (String) -> Unit = {},
    onFindInMapButtonClicked: (Location) -> Unit = {},
    modifier: Modifier = Modifier
) {
    VerificationCard(modifier = modifier
        .fillMaxWidth(),
        menuItems = listOf(
            ArsaDropDownMenuState(0, "ارسال پیام به مشتری")
        ),
        onItemSelected = {
            when (it) {
                0 -> onSendMessageButtonClicked(salonOwnerUser.username)
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            ArsaInfoRow(title = "آیدی آرایشگاه: ", info = salonOwnerUser.username)
            ArsaInfoRow(title = "نام آرایشگاه: ", info = salonOwnerUser.dresserName)
            ArsaInfoRow(title = "آدرس آرایشگاه: ", info = salonOwnerUser.address)

            Text(
                text = "مدارک ارسالی:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ArsaButton(
                    text = "چهره",
                    onButtonClicked = { onViewImageClicked(salonOwnerUser.authentication) })
                ArsaButton(
                    text = "پروانه کسب",
                    onButtonClicked = { onViewImageClicked(salonOwnerUser.businessLicense) })
                ArsaButton(
                    text = "پروفایل",
                    onButtonClicked = { onViewImageClicked(salonOwnerUser.profile) }
                )
                ArsaButton(
                    text = "نقشه",
                    onButtonClicked = {
                        val location = Location("Provider")
                        location.latitude = salonOwnerUser.lat
                        location.longitude = salonOwnerUser.lon
                        onFindInMapButtonClicked(location)
                    }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
            VerifyOrDenyButtonRow(
                onVerifyButtonClicked = onVerifyButtonClicked,
                onDenyButtonClicked = onDenyButtonClicked
            )
        }
    }
}

@Composable
fun FullCommentCard(
    salonComment: SalonCommentDTO,
    onSendMessageButtonClicked: (customerId: String) -> Unit = {},
    onVerifyButtonClicked: () -> Unit = {},
    onDenyButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    VerificationCard(modifier = modifier
        .fillMaxWidth(),
        menuItems = listOf(
            ArsaDropDownMenuState(0, "ارسال پیام به مشتری")
        ),
        onItemSelected = {
            when (it) {
                0 -> onSendMessageButtonClicked(salonComment.customerId)
            }
        }
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            ArsaInfoRow(title = "نام کاربر: ", info = salonComment.customerFirstname + " " + salonComment.customerLastname)
            ArsaInfoRow(title = "نام صاحب آرایشگاه: ", info = salonComment.stylistFirstname + salonComment.customerLastname)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "امتیاز کلی: ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(5.dp))
                ArsaRatingBar(rating = salonComment.star.toFloat())
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "نظر: ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = salonComment.description,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 5
                )
            }
            Text(
                text = "جزییات: ",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 5
            )

            ArsaTagRow(
                arsaTagGroupState = ArsaTagState(
                    label = "نقاط قوت آرایشگاه",
                    selectedTags = salonComment.goodServices.split("_")
                )
            )

            ArsaTagRow(
                arsaTagGroupState = ArsaTagState(
                    label = "نقاط ضعف آرایشگاه",
                    selectedTags = salonComment.badServices.split("_")
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            VerifyOrDenyButtonRow(
                onVerifyButtonClicked = onVerifyButtonClicked,
                onDenyButtonClicked = onDenyButtonClicked
            )
        }
    }
}

@Composable
fun VerifyOrDenyButtonRow(
    onVerifyButtonClicked: () -> Unit = {},
    onDenyButtonClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ArsaButton(text = "تایید", onButtonClicked = onVerifyButtonClicked)
        Spacer(modifier = Modifier.width(5.dp))
        ArsaButton(text = "رد", onButtonClicked = onDenyButtonClicked)
    }
}

@Composable
fun ArsaInfoRow(
    title: String,
    info: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = info,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 5
        )
    }
}
@Composable
fun RatingWithLabel(
    serviceRating: ServiceRating,
    maxRating: Float = 5.0f,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(3f),
            text = serviceRating.service,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = serviceRating.rating.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            LinearProgressIndicator(
                modifier = Modifier.weight(5f),
                progress = serviceRating.rating.div(maxRating),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.tertiary,
                strokeCap = StrokeCap.Round
            )
        }
    }
}

/**
 * previews
 */
/**
 * previews
 */

//@AllSizeDevicePreview
@Composable
fun VerificationCardPreview() {
    ArsaStyleAdminTheme {
        VerificationCard(
            menuItems = listOf(
                ArsaDropDownMenuState(1, "مشاهده جزییات"),
                ArsaDropDownMenuState(1, "ارسال پیام به مشتری")
            )
        )
    }
}
//@AllSizeDevicePreview
@Composable
fun FullCommentPreview() {
    ArsaStyleAdminTheme {
//        FullCommentCard(
//            salonComment =
//        )
    }
}
@AllSizeDevicePreview
@Composable
fun SalonVerificationCardPreview() {
    ArsaStyleAdminTheme {
    }
}

@AllSizeDevicePreview
@Composable
fun ServiceVerificationCardPreview() {
    ArsaStyleAdminTheme {
        ServiceVerificationCard(
            salonService =
            SalonService(
                id = 1,
                salonId = "nooshin12",
                name = "لاک ناخن",
                category = "nail",
                price = "120.000",
                minTime = 12,
                maxTime = 30
            )
        )
    }
}
