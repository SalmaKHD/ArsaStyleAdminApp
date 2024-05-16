package com.salmakhd.android.arsastyleadmin.common.model
import java.time.LocalDateTime

data class Appointment (
    val id: String,
    val salon: Salon? = null,
    val customerName: String? = null,
    val timeDate: LocalDateTime,
    val appointmentStatus: ArsaOperationStatus = ArsaOperationStatus.UNKNOWN,
    val selectedServices: List<SalonService> = emptyList(),
    val totalCost: String = ""
)