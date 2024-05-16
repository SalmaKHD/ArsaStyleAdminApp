package com.salmakhd.android.arsastyleadmin.server.responseModel

import com.google.gson.annotations.SerializedName
import com.salmakhd.android.arsastyleadmin.server.responseModel.ServicesModel

data class UserModel(
    @SerializedName("user_id") val id: Int = -1,
    @SerializedName("firstname") val firstname: String = "",
    @SerializedName("lastname") val lastname: String = "",
    @SerializedName("phone") val phoneNumber: String = "",
    @SerializedName("codeMeli") val codeMeli: String = "",
    @SerializedName("birthday") val birthday: String = "",
    @SerializedName("province") val province: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("username") val username: String = "",
    @SerializedName("side") val side: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("profile") val profile: String= "",
    @SerializedName("invite_code") val inviteCode: String="",
    @SerializedName("invited_code") val invitedCode: String="",
    @SerializedName("dresser_name") val dresserName: String="",
    @SerializedName("lat") val lat: Double = 0.0,
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("address") val address: String = "",
    @SerializedName("AD_date") val adDate: String = "",
    @SerializedName("solar_date") val solarDate: String = "",
    @SerializedName("FirebaseToken") val firebaseToken: String = "",
    @SerializedName("status") val status: Int = -1,
    @SerializedName("services") val services: List<ServicesModel> = emptyList()
)