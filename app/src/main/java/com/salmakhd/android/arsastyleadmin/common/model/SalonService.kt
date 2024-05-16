package com.salmakhd.android.arsastyleadmin.common.model

data class SalonService(
    val id: Int,
    val salonId: String = "",
    val name: String,
    val category: String,
    val price: String,
    val pictureUrl: String? = null,
    val minTime: Int = 0,
    val maxTime: Int = 0
)