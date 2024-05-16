package com.salmakhd.android.arsastyleadmin.ui.designsystem

import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.salmakhd.android.arsastyleadmin.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArsaComplexHorizontalPagerGraph(
    modifier: Modifier = Modifier,
    statisticsList: List<List<Int>> = emptyList(),
    tabs: List<ArsaTabState>,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        statisticsList.size
    }

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    val chartEntryModelProducer = remember(selectedTab) {
        ChartEntryModelProducer(
            (statisticsList[selectedTab])
                .mapIndexed { index, value ->
                    entryOf(index+1, value)
                }
        )
    }

    LaunchedEffect(key1 = selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                tabs.forEachIndexed { index, tabState ->
                    ArsaTab(
                        modifier = Modifier.weight(1f),
                        text = tabState.title,
                        isCardSelected = selectedTab == index,
                        onCardClicked = { selectedTab = index },
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
//
//            ArsaTabRow(
//                tabs = listOf(
//                    ArsaTabState(
//                        title = "درآمد",
//                        iconRes = R.drawable.ic_money_bag
//                    ),
//                    ArsaTabState(
//                        title = "تعداد مشتری",
//                        iconRes = R.drawable.ic_total_customers
//                    )
//                ),
//                containerColor = Color.Transparent,
//                selectedTab = selectedTab,
//                onTabChanged = { selectedTab = it }
//            )

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                HorizontalPager(state = pagerState) {
                    Chart(
                        modifier = Modifier.padding(top=10.dp, bottom = 10.dp, end= 10.dp, start = 5.dp),
                        chart = columnChart(),
                        chartModelProducer = chartEntryModelProducer,
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(),
                    )

//                    val selectedList = remember(selectedTab) {
//                        when(selectedTab) {
//                            0 -> {
//                                incomeList
//                            }
//                            else -> {
//                                totalCustomersList
//                            }
//                        }
//                    }
//                    ArsaGraph(
//                        modifier = Modifier.height(200.dp),
//                        valuesList = selectedList,
//                        highlightedBarIndex = LocalDate.now().monthValue-1,
//                        labelsList =  listOf(
//                            "ف", "ا", "خ", "ت", "م", "ش", "م", "آ", "آ", "د", "ب", "ا"
//                        ),
//                        scale = selectedList.maxOrNull()?.plus(10) ?: 1,
//                        labelStyle = MaterialTheme.typography.labelSmall
//                    )
                }
            }
        }
    }
}

@AllSizeDevicePreview
@Composable
fun ArsaGraphPreview() {
    ArsaStyleAdminTheme {
        ArsaComplexHorizontalPagerGraph(
            statisticsList = listOf(
                listOf(
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
                ),  listOf(
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
            ),
            tabs = listOf(
                ArsaTabState(
                    title = "dsd",
                    iconRes = R.drawable.ic_money_bag
                ),
                ArsaTabState(
                    title = "dsd",
                    iconRes = R.drawable.ic_money_bag
                )
            )
        )
    }
}
