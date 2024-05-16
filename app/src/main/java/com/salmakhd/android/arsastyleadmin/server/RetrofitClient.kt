package com.salmakhd.android.arsastyleadmin.server

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.salmakhd.android.arsastyleadmin.common.utility.ArsaStyleConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    @ApplicationContext context: Context
) {

    private val retrofit: Retrofit

    init {
        // new
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        //
        retrofit = Retrofit.Builder()
            .baseUrl(ArsaStyleConstants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object {
        private var instance: RetrofitClient? = null

        @Synchronized
        fun getInstance(context: Context): RetrofitClient {
            if (instance == null) {
                instance = RetrofitClient(context)
            }
            return instance!!
        }
    }

    fun getApi(): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}