package com.salmakhd.android.arsastyleadmin.server.model

import com.google.gson.annotations.SerializedName

data class ServiceDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("stylist_id")
    val stylistId: String,
    @SerializedName("provider_id_1")
    val providerId1: String,
    @SerializedName("provider_id_2")
    val providerId2: String,
    @SerializedName("provider_id_3")
    val providerId3: String,
    @SerializedName("provider_id_4")
    val providerId4: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("service_name")
    val serviceName: String,
    val amount: Long, // Assuming amount is a monetary value
    @SerializedName("min_time")
    val minTime: String,
    @SerializedName("max_time")
    val maxTime: String,
    @SerializedName("date_miladi")
    val miladiDate: String, // Using more descriptive name
    @SerializedName("date_shamsi")
    val shamsiDate: String, // Using more descriptive name
    val status: String,
    @SerializedName("provider1_firstname")
    val provider1FirstName: String,
    @SerializedName("provider1_lastname")
    val provider1LastName: String,
    @SerializedName("provider1_username")
    val provider1Username: String,
    @SerializedName("provider2_firstname")
    val provider2FirstName: String?, // Using nullable type for null values
    @SerializedName("provider2_lastname")
    val provider2LastName: String?,
    @SerializedName("provider2_username")
    val provider2Username: String?,
    @SerializedName("provider3_firstname")
    val provider3FirstName: String?,
    @SerializedName("provider3_lastname")
    val provider3LastName: String?,
    @SerializedName("provider3_username")
    val provider3Username: String?,
    @SerializedName("provider4_firstname")
    val provider4FirstName: String?,
    @SerializedName("provider4_lastname")
    val provider4LastName: String?,
    @SerializedName("provider4_username")
    val provider4Username: String?
)
