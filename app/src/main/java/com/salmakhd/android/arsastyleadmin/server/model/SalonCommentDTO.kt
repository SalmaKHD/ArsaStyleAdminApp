package com.salmakhd.android.arsastyleadmin.server.model

import com.google.gson.annotations.SerializedName

data class SalonCommentDTO(
    @SerializedName("id") val id: String,
    @SerializedName("customer_id") val customerId: String,
    @SerializedName("stylist_id") val stylistId: String,
    @SerializedName("customer_firstname") val customerFirstname: String,
    @SerializedName("customer_lastname") val customerLastname: String,
    @SerializedName("stylist_firstname") val stylistFirstname: String,
    @SerializedName("stylist_lastname") val stylistLastname: String,
    @SerializedName("reservation_id") val reservationId: String,
    @SerializedName("star") val star: String,
    val description: String,
    val goodServices: String,
    val badServices: String,
    @SerializedName("date_miladi") val miladiDate: String,
    @SerializedName("date_shamsi") val shamsiDate: String,
    @SerializedName("status") val status: String
)
