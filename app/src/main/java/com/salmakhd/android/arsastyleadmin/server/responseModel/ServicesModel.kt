package com.salmakhd.android.arsastyleadmin.server.responseModel

import com.google.gson.annotations.SerializedName

data class ServicesModel (
    @SerializedName("service_id") val id: Int,
    @SerializedName("stylist_id") val stylistId: Int,
    @SerializedName("category") val category: String,
    @SerializedName("service_name") val serviceName: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("min_time") val minTime: String ,
    @SerializedName("max_time") val maxTime: String ,
    @SerializedName("AD_date") val adDate: String ,
    @SerializedName("solar_date") val solarDate: String ,
)