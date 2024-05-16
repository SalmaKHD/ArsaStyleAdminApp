package com.salmakhd.android.arsastyleadmin.screen.routes

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.common.model.ArsaReportType
import com.salmakhd.android.arsastyleadmin.common.model.ArsaStyleRole
import com.salmakhd.android.arsastyleadmin.common.model.Report
import com.salmakhd.android.arsastyleadmin.common.utility.gregorianToJalali
import com.salmakhd.android.arsastyleadmin.ui.designsystem.AllSizeDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaBasicScreen
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaButton
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDropDownMenuState
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabRow
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaTabState
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import java.time.LocalDate

@Composable
fun UserSupportScreen() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    ArsaStyleAdminTheme {
        ArsaBasicScreen(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            shouldBeScrollable = false,
            shouldHaveHorizontalPadding = false
        ) {
            ArsaTabRow(
                tabs = listOf(
                    ArsaTabState(
                        title = "گزارشات",
                        iconRes = R.drawable.ic_report
                    ),
                    ArsaTabState(
                        title = "سوال ها",
                        iconRes = R.drawable.ic_question_circle
                    )
                ),
                onTabChanged = { selectedTabIndex = it },
                selectedTab = selectedTabIndex,
                containerColor = Color.Transparent
            )
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedContent(targetState = selectedTabIndex) {
                when(it) {
                    0 -> {
                        LazyColumn(modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            items(listOf(
                                Report(
                                    id = 1,
                                    username = "nooshin12",
                                    userRole = ArsaStyleRole.CUSTOMER,
                                    issueDate = LocalDate.now(),
                                    reportType = ArsaReportType.COMPLAINT,
                                    reportText = "ارایشگرم رو دوست نداشتم و پولم رو می خوام پس بگیرم"
                                ),
                                Report(
                                    id = 2,
                                    username = "navadeep34",
                                    userRole = ArsaStyleRole.STYLIST,
                                    issueDate = LocalDate.now().minusDays(4),
                                    reportType = ArsaReportType.BUG_REPORT,
                                    reportText = "دکمه عقب تو داشبورد از برنامه بیرون نمیاره"
                                )
                            ),
                                key = {it.id}
                            ) {
                                ReportCard(
                                    modifier = Modifier.fillMaxSize(0.9f),
                                    report = it
                                )
                            }
                        }
                    }
                    1 -> {
                        LazyColumn(modifier = Modifier
                            .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(listOf(
                                Report(
                                    id = 1,
                                    username = "nooshin12",
                                    userRole = ArsaStyleRole.STYLIST,
                                    issueDate = LocalDate.now(),
                                    reportType = ArsaReportType.QUESTION,
                                    reportText = "چجوری بیشتر از 2 روز از اپ بیرون نیام؟"
                                )
                            ),
                                key = {it.id}
                            ) {
                                QuestionCard(
                                    report = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionCard(
    report: Report,
    onSendMessageButtonClicked: (reportId: Int) -> Unit = {},
    onResolvedButtonClicked: () -> Unit = {},
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
            ArsaInfoRow(title = "شناسه کاربر: ", info = report.username)
            ArsaInfoRow(title = "نقش: " , info =
            when(report.userRole) {
                ArsaStyleRole.CUSTOMER -> "مشتری"
                else -> "آرایشگر"
            },)
            ArsaInfoRow(title = "تاریخ: ", info = gregorianToJalali(
                report.issueDate.year,
                report.issueDate.monthValue,
                report.issueDate.dayOfMonth
            ))
            ArsaInfoRow(title = "سوال:", info =report.reportText)
            Spacer(modifier = Modifier.height(20.dp))
            ArsaButton(text = "پاسخ بده", onButtonClicked = {onSendMessageButtonClicked(report.id)})
        }
    }
}

@Composable
fun ReportCard(
    report: Report,
    onSendMessageButtonClicked: (username: String) -> Unit = {},
    onResolvedButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    VerificationCard(modifier = modifier
        .fillMaxWidth(),
        menuItems = listOf(
            ArsaDropDownMenuState(0, "ارسال پیام به مشتری")
        ),
        onItemSelected = {
            when (it) {
                0 -> onSendMessageButtonClicked(report.username)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ArsaInfoRow(title = "شناسه کاربر: ", info = report.username)
            ArsaInfoRow(title = "نقش: " , info =
            when(report.userRole) {
                ArsaStyleRole.CUSTOMER -> "مشتری"
                else -> "آرایشگر"
            },)
            ArsaInfoRow(title = "تاریخ: ", info = gregorianToJalali(
                report.issueDate.year,
                report.issueDate.monthValue,
                report.issueDate.dayOfMonth
            ))
            ArsaInfoRow(title = "نوع گزارش:", info =
            when(report.reportType) {
                ArsaReportType.BUG_REPORT -> "باگ برنامه"
                else -> "شکایت"
            })
            ArsaInfoRow(title = "گزارش:", info = report.reportText)

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ArsaButton(text = "حل شد", onButtonClicked = onResolvedButtonClicked)
            }
        }
    }
}

@AllSizeDevicePreview
@Composable
fun ReportCardPreview() {
    ArsaStyleAdminTheme {
        ReportCard(
            report = Report(
                id = 1,
                username = "nooshin12",
                userRole = ArsaStyleRole.CUSTOMER,
                issueDate = LocalDate.now(),
                reportType = ArsaReportType.COMPLAINT,
                reportText = "ارایشگرم رو دوست نداشتم و پولم رو می خوام پس بگیرم"
            )
        )
    }
}

@AllSizeDevicePreview
@Composable
fun QuestionCardPreview() {
    ArsaStyleAdminTheme {
        QuestionCard(
            report = Report(
                id = 1,
                username = "nooshin12",
                userRole = ArsaStyleRole.STYLIST,
                issueDate = LocalDate.now(),
                reportType = ArsaReportType.QUESTION,
                reportText = "چجوری بیشتر از 2 روز از اپ بیرون نیام؟"
            )
        )
    }
}