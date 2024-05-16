package com.salmakhd.android.arsastyleadmin.screen.routes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.common.model.ArsaOperationStatus
import com.salmakhd.android.arsastyleadmin.common.model.ArsaStyleRole
import com.salmakhd.android.arsastyleadmin.common.model.ArsaTransactionFrequency
import com.salmakhd.android.arsastyleadmin.common.model.ArsaTransactionType
import com.salmakhd.android.arsastyleadmin.common.model.TransactionRecord
import com.salmakhd.android.arsastyleadmin.common.utility.gregorianToJalali
import com.salmakhd.android.arsastyleadmin.ui.designsystem.AllSizeDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaBasicScreen
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaComplexHorizontalPagerGraph
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDivider
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaInfiniteLottieAnimation
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaOutlinedCard
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabRow
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.SmallDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen
import java.time.LocalDate

//@AllSizeDevicePreview
@Composable
fun WalletScreen(
    navigateToRatingScreen: () -> Unit = {},
    navigateToAddServicesScreen: () -> Unit = {},
    navigateToWorkHoursScreenSelectionScreen: () -> Unit = {},
) {
    ArsaStyleAdminTheme {
        ArsaBasicScreen(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            shouldBeScrollable = false,
            shouldHaveHorizontalPadding = false
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaTopPadding))
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "کیف پول",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaTopPadding))

            StylistStatisticsArea(
                incomeList = listOf(
                    300,
                    300,
                    700,
                    1000,
                    100,
                    100,
                    100,
                    700,
                    600,
                    1200,
                    100,
                    300
                ), totalCustomersList = listOf(
                    100,
                    200,
                    1000,
                    100,
                    20,
                    1,
                    10,
                    10,
                    200,
                    300,
                    100,
                    600
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "تراکنش های اخیر",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(20.dp))

            RecentTransactions(transactios =
            listOf(
                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.SUCCESS,
                    id = 0,
                    amount = 0.0,
                    type = ArsaTransactionType.DEPOSIT,
                    username = "nooshin12"
                ),
                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.FAILURE,
                    id = 1,
                    amount = 30.0,
                    type = ArsaTransactionType.WITHDRAW,
                    frequency = ArsaTransactionFrequency.MONTHLY,
                    username = "nooshin12"
                ),

                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.FAILURE,
                    id = 1,
                    amount = 30.0,
                    username = "nooshin12"
                )
            ))
        }
    }
}

@Composable
fun StylistStatisticsArea(
    modifier: Modifier = Modifier,
    incomeList: List<Int>,
    totalCustomersList: List<Int>
) {
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "آمار حساب در سال گذشته",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        ArsaComplexHorizontalPagerGraph(
            tabs = listOf(
                ArsaTabState(
                    title = "درآمد"
                ),
                ArsaTabState(
                    title = "تعداد کاربران"
                )
            ) ,
            statisticsList = listOf(
                incomeList,
                totalCustomersList
            )
        )
    }
}

@Composable
fun RecentTransactions(
    modifier: Modifier = Modifier,
    shouldShowFullDetails: Boolean = false,
    transactios: List<TransactionRecord>
) {
    ArsaOutlinedCard() {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (transactios.isEmpty()) {
                ArsaInfiniteLottieAnimation(
                    animationRes = R.raw.anim_wallet,
                    modifier = Modifier.requiredSize(100.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "هیچ تراکنشی موجود نیست",
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(transactios) { index, transaction ->
                        TransactionRow(
                            transaction = transaction,
                            shouldShowFullDetails = shouldShowFullDetails
                        )

                        if (index != transactios.size - 1) {
                            Spacer(modifier = Modifier.height(5.dp))
                            ArsaDivider()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TransactionRow(
    modifier: Modifier = Modifier,
    transaction: TransactionRecord,
    shouldShowFullDetails: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "تاریخ: ${
                    gregorianToJalali(
                        transaction.date.year,
                        transaction.date.monthValue,
                        transaction.date.dayOfMonth
                    )
                }",
                style = MaterialTheme.typography.titleSmall
            )
            if(shouldShowFullDetails) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "نوع: " +
                            when(transaction.type) {
                                ArsaTransactionType.DEPOSIT -> "واریز"
                                ArsaTransactionType.WITHDRAW -> "برداشت "
                            } +
                            if(transaction.type != ArsaTransactionType.DEPOSIT) {
                                when (transaction.frequency) {
                                    ArsaTransactionFrequency.ONETIME -> "یک باره"
                                    ArsaTransactionFrequency.MONTHLY -> "دوره ای یک ماهه"
                                    ArsaTransactionFrequency.WEEKLY -> "دوره ای هفتگی"
                                }
                            } else "",
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "مبلغ: ${transaction.amount}",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "شناسه کاربر: " + transaction.username,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "نقش: " +
                        when(transaction.role) {
                            ArsaStyleRole.CUSTOMER -> "مشتری"
                            else -> "آرایشگر"
                        },
                style = MaterialTheme.typography.titleSmall
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(10.dp),
                    imageVector = Icons.Filled.Circle,
                    tint = when (transaction.status) {
                        ArsaOperationStatus.SUCCESS -> Color.Green
                        ArsaOperationStatus.FAILURE -> Color.Red
                        ArsaOperationStatus.PENDING -> Color.Yellow
                        ArsaOperationStatus.UNKNOWN -> Color.Blue
                    },
                    contentDescription = "Transaction Status Indicator"
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text =
                    when (transaction.status) {
                        ArsaOperationStatus.SUCCESS -> "موفق"
                        ArsaOperationStatus.FAILURE -> "ناموفق"
                        ArsaOperationStatus.PENDING -> "در حال انجام"
                        ArsaOperationStatus.UNKNOWN -> "نامعلوم"
                    },
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
@SmallDevicePreview
fun RecentTransactionsPreview() {
    ArsaStyleAdminTheme {
        RecentTransactions(
            transactios = listOf(
                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.SUCCESS,
                    id = 0,
                    amount = 0.0,
                    type = ArsaTransactionType.DEPOSIT,
                ),
                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.FAILURE,
                    id = 1,
                    amount = 30.0,
                    type = ArsaTransactionType.WITHDRAW,
                    frequency = ArsaTransactionFrequency.MONTHLY
                ),

                TransactionRecord(
                    date = LocalDate.now(),
                    status = ArsaOperationStatus.FAILURE,
                    id = 1,
                    amount = 30.0
                )
            ),
            shouldShowFullDetails = true
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StylistGraphArea(
    modifier: Modifier = Modifier,
    incomeList: List<Int>,
    totalCustomersList: List<Int>
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        2
    }

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = selectedTab) {
        pagerState.animateScrollToPage(
            when(selectedTab) {
                0 -> 0
                else -> 1
            }
        )
    }
    // TODO: for updating the chart
    /*
    LaunchedEffect(key1 = incomeList) {
        chartEntryModelProducer.setEntries(incomeList.mapIndexed { index, value ->
            entryOf(index+1, value)
        }
        )
    }
     */

    ArsaOutlinedCard(surfaceModifier = modifier
        .fillMaxWidth(0.8f)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ArsaTabRow(
                tabs = listOf(
                    ArsaTabState(
                        title = "درآمد",
                        iconRes = R.drawable.ic_money_bag
                    ),
                    ArsaTabState(
                        title = "تعداد کاربران",
                        iconRes = R.drawable.ic_total_customers
                    )
                ),
                containerColor = Color.Transparent,
                selectedTab = selectedTab,
                onTabChanged = { selectedTab = it }
            )

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                HorizontalPager(state = pagerState) {
                }
            }
        }
    }
}

/**
 * previews
 */
@AllSizeDevicePreview
@Composable
fun StylistInitialInstructionsDialogPreview() {
    ArsaStyleAdminTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        }
    }
}

//@MediumDevicePreview
@Composable
fun StylistGraphAreaPreview() {
    ArsaStyleAdminTheme {
        StylistGraphArea(
            incomeList = listOf(
                300,
                300,
                700,
                1000,
                100,
                100,
                100,
                700,
                600,
                1200,
                100,
                300
            ), totalCustomersList = listOf(
                100,
                200,
                1000,
                100,
                20,
                1,
                10,
                10,
                200,
                300,
                100,
                600
            )
        )
    }
}

