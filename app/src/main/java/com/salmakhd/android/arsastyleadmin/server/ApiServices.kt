package com.salmakhd.android.arsastyleadmin.server

import com.salmakhd.android.arsastyleadmin.server.model.SalonCommentDTO
import com.salmakhd.android.arsastyleadmin.server.model.ServiceDTO
import com.salmakhd.android.arsastyleadmin.server.model.StylistDTO
import com.salmakhd.android.arsastyleadmin.server.responseModel.MessageResponse
import com.salmakhd.android.arsastyleadmin.server.responseModel.UserModelResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @FormUrlEncoded
    @POST("LoginUser.php") fun loginUser(
        @Field("Username") username:String ,
        @Field("Password") password:String ,
        @Field("Token") token:String
    ):Call<UserModelResponse>

    @FormUrlEncoded
    @POST("RegisterUser.php")
    fun registerUser(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("codeMeli") codeMeli: String,
        @Field("birthday") birthday: String,
        @Field("province") province: String,
        @Field("city") city: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("side") side: String,
        @Field("FirebaseToken") token: String,
        @Field("invitedCode") invitedCode: String,
        @Field("profile") profile: String,
        @Field("address") address: String,
        @Field("lat") lat: String,
        @Field("lon") lon: String,
        @Field("dresser_name") dresserName: String,
        @Field("authentication") authentication: String,
        @Field("business_license") businessLicense: String
    ): Call<UserModelResponse>

    @FormUrlEncoded
    @POST("getStylistAdmin.php")
    fun getStylists(
        @Field("password") password:String,
    ):Call<MessageResponse<List<StylistDTO>>>

    @FormUrlEncoded
    @POST("getRatingAdmin.php")
    fun getComments(
        @Field("password") password:String,
    ):Call<MessageResponse<List<SalonCommentDTO>>>

    @FormUrlEncoded
    @POST("getServicesAdmin.php")
    fun getServices(
        @Field("password") password:String,
    ):Call<MessageResponse<List<ServiceDTO>>>

    @FormUrlEncoded
    @POST("AcceptStylistAdmin.php")
    fun acceptStylist(
        @Field("password") password:String,
        @Field("stylist_id") stylistId : String,
    ):Call<MessageResponse<Any>>

    @FormUrlEncoded
    @POST("AcceptRatingAdmin.php")
    fun acceptComment(
        @Field("password") password:String,
        @Field("rating_id") commentId : String,
    ):Call<MessageResponse<Any>>

    @FormUrlEncoded
    @POST("AcceptServicesAdmin.php")
    fun acceptService(
        @Field("password") password:String,
        @Field("service_id") serviceId : String,
    ):Call<MessageResponse<Any>>



    @FormUrlEncoded
    @POST("getStylistData.php")
    fun getReports(
        @Field("password") password:String,
    ):Call<MessageResponse<Any>>



    @FormUrlEncoded
    @POST("getStylistData.php")
    fun getTransactions(
        @Field("password") password:String,
    ):Call<MessageResponse<Any>>
}