package com.salmakhd.android.arsastyleadmin.server.responseModel

import com.google.gson.annotations.SerializedName

data class UserModelResponse (
    @SerializedName("message")val message:String,
    @SerializedName("ResponseCode")val responseCode:String,
    @SerializedName("Data")val data: List<UserModel>
)

data class MessageResponse <T> (
    @SerializedName("message")val message:String,
    @SerializedName("ResponseCode")val responseCode:String,
    @SerializedName("Data")val data: T
)