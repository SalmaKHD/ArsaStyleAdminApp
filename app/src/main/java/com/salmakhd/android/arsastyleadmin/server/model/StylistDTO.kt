package com.salmakhd.android.arsastyleadmin.server.model

import com.google.gson.annotations.SerializedName

data class StylistDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("firstname")
    val firstName: String,

    @SerializedName("lastname")
    val lastName: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("code_meli")
    val codeMeli: String,

    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("province")
    val province: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("side")
    val side: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("profile")
    val profile: String,

    @SerializedName("invite_code")
    val inviteCode: String,

    @SerializedName("invited_code")
    val invitedCode: String,

    @SerializedName("dresser_name")
    val dresserName: String,

    @SerializedName("authentication")
    val authentication: String,

    @SerializedName("business_license")
    val businessLicense: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lon")
    val lon: Double,

    @SerializedName("address")
    val address: String,

    @SerializedName("signup_date_miladi")
    val signupDateMiladi: String,

    @SerializedName("signup_date_shamsi")
    val signupDateShamsi: String
)