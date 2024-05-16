package com.salmakhd.android.arsastyleadmin.common.model

import java.time.LocalDate

data class TransactionRecord (
    val id: Int,
    val username: String = "",
    val role: ArsaStyleRole = ArsaStyleRole.CUSTOMER,
    val date: LocalDate, // original type: Instant
    val status: ArsaOperationStatus,
    val type: ArsaTransactionType = ArsaTransactionType.DEPOSIT,
    val frequency: ArsaTransactionFrequency = ArsaTransactionFrequency.ONETIME,
    val amount: Double,
)

enum class ArsaTransactionType{
    DEPOSIT,
    WITHDRAW
}

enum class ArsaTransactionFrequency{
    ONETIME,
    WEEKLY,
    MONTHLY
}