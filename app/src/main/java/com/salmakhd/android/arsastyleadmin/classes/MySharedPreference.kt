package com.salmakhd.android.arsastyleadmin.classes

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.salmakhd.android.arsastyleadmin.server.responseModel.UserModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Singleton
    fun providesEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): MySharedPreference = MySharedPreference.getInstance(context)
}

class MySharedPreference private constructor(
    context: Context
) {
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    private var masterKeyAlias: String

    init {
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                "arsastyle_file",
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        editor = sharedPreferences.edit()
    }
    companion object {
        private var instance: MySharedPreference? = null
        fun getInstance(context: Context): MySharedPreference {
            if (instance == null) {
                instance = MySharedPreference(context)
            }
            return instance!!
        }
    }
    fun clearSharedPreferences() {
        editor.clear().commit()
    }

    fun setLogin( IsLogin: Boolean) {
        editor.putBoolean("IsLogin", IsLogin).apply()
    }

    fun setInitialInstructionDialogShown(isShown: Boolean) {
        editor.putBoolean("isInitialInstructionDialogShown", isShown).apply()
    }

    fun setUser(user: UserModel) {
        editor.putInt("user_id", user.id)
        editor.putString("firstname", user.firstname)
        editor.putString("lastname", user.lastname)
        editor.putString("phone", user.phoneNumber)
        editor.putString("codeMeli", user.codeMeli)
        editor.putString("birthday", user.birthday)
        editor.putString("province", user.province)
        editor.putString("city", user.city)
        editor.putString("username", user.username)
        editor.putString("side", user.side)
        editor.putString("gender", user.gender)
        editor.putString("profile", user.profile)
        editor.putString("invite_code", user.inviteCode)
        editor.putString("invited_code", user.invitedCode)
        editor.putString("dresser_name", user.dresserName)
        editor.putFloat("lat", user.lat.toFloat())
        editor.putFloat("lon", user.lon.toFloat())
        editor.putString("dresser_name", user.dresserName)
        editor.putString("address", user.address)
        editor.putString("AD_date", user.adDate)
        editor.putString("solar_date", user.solarDate)
        editor.putString("FirebaseToken", user.firebaseToken)
        editor.putInt("status", user.status)
        // editor.putString("services", user.services)
    }

    fun getUser(): UserModel =
        UserModel(
            id = sharedPreferences.getInt("user_id", -1),
            firstname = sharedPreferences.getString("firstname", "null")?: "null",
            lastname = sharedPreferences.getString("lastname", "null")?: "null",
            phoneNumber = sharedPreferences.getString("phone", "null") ?: "null",
            codeMeli = sharedPreferences.getString("codeMeli", "null")?: "null",
            birthday = sharedPreferences.getString("birthday", "null")?: "null",
            province = sharedPreferences.getString("province", "null")?: "null",
            city = sharedPreferences.getString("city", "null")?: "null",
            username = sharedPreferences.getString("username", "null")?: "null",
            side = sharedPreferences.getString("side", "null")?: "null",
            gender = sharedPreferences.getString("gender", "null")?: "null",
            profile = sharedPreferences.getString("profile", "null")?: "null",
            invitedCode = sharedPreferences.getString("invite_code", "null")?: "null",
            inviteCode = sharedPreferences.getString("invited_code", "null")?: "null",
            dresserName = sharedPreferences.getString("dresser_name", "null")?: "null",
            lat = sharedPreferences.getFloat("lat", 0f).toDouble()?: 0.0,
            lon = sharedPreferences.getFloat("lon", 0f).toDouble() ?: 0.0,
            address = sharedPreferences.getString("address", "null")?: "null",
            adDate = sharedPreferences.getString("AD_date", "null")?: "null",
            solarDate = sharedPreferences.getString("solar_date", "null")?: "null",
            firebaseToken = sharedPreferences.getString("FirebaseToken", "null")?: "null",
            status = sharedPreferences.getInt("status", -1)?: -1,
            services = emptyList()
        )

    fun getLogin(): Boolean {
        return sharedPreferences.getBoolean("IsLogin", false)
    }

    fun getIsInitialInstructionDialogShown(): Boolean {
        return sharedPreferences.getBoolean("InitialInstructionDialogShown", false)
    }
}