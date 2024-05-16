package com.salmakhd.android.arsastyleadmin.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Province (
    val provinceName: String,
    val provinceId: String
)

@Serializable
data class City(
    val cityName: String = "تهران",
    val provinceId: String = "23",
)