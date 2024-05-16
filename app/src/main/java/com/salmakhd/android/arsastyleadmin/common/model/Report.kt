package com.salmakhd.android.arsastyleadmin.common.model

import java.time.LocalDate

data class Report(
    val id: Int,
    val username: String,
    val userRole: ArsaStyleRole = ArsaStyleRole.CUSTOMER,
    val issueDate: LocalDate,
    val reportType: ArsaReportType,
    val reportText: String
)

enum class ArsaReportType {
    BUG_REPORT,
    COMPLAINT,
    QUESTION
}