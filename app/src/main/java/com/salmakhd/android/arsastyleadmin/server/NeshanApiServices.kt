package com.salmakhd.android.arsastyleadmin.server

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NeshanApiServices {
    @GET("reverse")
    fun getAddressFromLocation(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Header("Api-Key") apiKey: String,
    )
}