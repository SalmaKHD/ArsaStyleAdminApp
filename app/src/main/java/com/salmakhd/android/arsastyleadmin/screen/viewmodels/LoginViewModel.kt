package com.salmakhd.android.arsastyleadmin.screen.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmakhd.android.arsastyleadmin.common.model.ArsaFieldType
import com.salmakhd.android.arsastyleadmin.common.model.ArsaScreenNetworkState
import com.salmakhd.android.arsastyleadmin.common.model.ArsaStyleRole
import com.salmakhd.android.arsastyleadmin.screen.routes.ArsaFieldState
import com.salmakhd.android.arsastyleadmin.screen.routes.LoginSectionState
import com.salmakhd.android.arsastyleadmin.server.responseModel.MessageResponse
import com.salmakhd.android.arsastyleadmin.classes.MySharedPreference
import com.salmakhd.android.arsastyleadmin.common.inputvalidation.validateBasicInput
import com.salmakhd.android.arsastyleadmin.common.utility.ArsaStyleConstants
import com.salmakhd.android.arsastyleadmin.server.ApiServices
import com.salmakhd.android.arsastyleadmin.server.responseModel.UserModelResponse
import com.salmakhd.android.arsastyleadmin.ui.theme.defaultRequestError
import com.salmakhd.android.arsastyleadmin.ui.theme.networkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiServices: ApiServices,
    private val mySharedPreference: MySharedPreference
): ViewModel() {
    var uiState by mutableStateOf(LoginState())
    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.FieldValueChanged -> {
                handleInput(event.value, event.fieldType)
            }
            is LoginEvent.LoginButtonClicked -> {
                val usernameValidationResult = validateBasicInput(uiState.userLoginState.userName.value?:"")
                val passwordValidationResult = validateBasicInput(uiState.userLoginState.password.value?: "")

                when {
                    !usernameValidationResult.successful -> {
                        uiState = uiState.copy(
                            userLoginState = uiState.userLoginState.copy(
                                userName = uiState.userLoginState.userName.copy(errorMessage = usernameValidationResult.errorMessage)
                            )
                        )
                    }
                    !passwordValidationResult.successful -> {
                        uiState = uiState.copy(
                            userLoginState = uiState.userLoginState.copy(
                                password = uiState.userLoginState.password.copy(errorMessage = passwordValidationResult.errorMessage)
                            )
                        )
                    }
                    else -> {
                        handleLogin(event)
                    }
                }
            }
        }
    }

    private fun handleLogin(event: LoginEvent.LoginButtonClicked) {
        authenticateUser(navigateToDashboardScreen = event.navigateToDashboardScreen)
    }
    private fun authenticateUser(navigateToDashboardScreen: (ArsaStyleRole) -> Unit = {}) {
        // set to empty state
        uiState = uiState.copy(
            networkState = ArsaScreenNetworkState.Loading,
            userLoginState = uiState.userLoginState.copy(loginErrorMessage = null)
        )

        apiServices.loginUser(
            uiState.userLoginState.userName.value.toString(),
            uiState.userLoginState.password.value.toString(),
            mySharedPreference.getUser().firebaseToken
        ).enqueue(object : Callback<UserModelResponse>{
            override fun onResponse(
                call: Call<UserModelResponse>,
                response: Response<UserModelResponse>
            ) {
                if(response.isSuccessful){
                    Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the isSuccussful block now")
                    // save to shared preferences
                    response.body()?.let {
                        viewModelScope.launch {
                            it.data.firstOrNull()?.let { user -> mySharedPreference.setUser(user) }
                            mySharedPreference.setLogin(true)
                            it.data.firstOrNull()?.let { user -> navigateToDashboardScreen(
                                ArsaStyleRole.CUSTOMER) }
                        }
                    }
                } else {
                    when(response.code()) {
                        406 -> {
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateLoginErrorMessage(response.body()?.message)
                        }
                        400 -> {
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateLoginErrorMessage(response.body()?.message)
                        }
                        500 -> {
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateLoginErrorMessage(response.body()?.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<UserModelResponse>, t: Throwable) {
                Log.d(ArsaStyleConstants.ARSA_LOG_TAG, t.message.toString())
                uiState = uiState.copy(
                    networkState = ArsaScreenNetworkState.Error(networkError) {
                        authenticateUser { navigateToDashboardScreen(it) }
                    }
                )
            }
        })
    }

    // do input validation, change state as needed
    private fun handleInput(value: String, fieldType: ArsaFieldType) {
        when(fieldType) {
            ArsaFieldType.USERNAME -> {
                uiState = uiState.copy(
                    userLoginState = uiState.userLoginState.copy(
                        userName = ArsaFieldState(
                            value = value
                        )
                    )
                )
            }
            else -> {
                uiState = uiState.copy(
                    userLoginState = uiState.userLoginState.copy(
                        password = ArsaFieldState(value = value)
                    )
                )
            }
        }
    }

    private fun updateLoginErrorMessage(errorMessage: String?) {
        uiState = uiState.copy(
            userLoginState = uiState.userLoginState.copy(
                loginErrorMessage = errorMessage ?: defaultRequestError
            ),
            networkState = ArsaScreenNetworkState.Idle
        )
    }
}

sealed interface LoginEvent {
    // action taken based on selected tab
    data class FieldValueChanged(val value: String, val fieldType: ArsaFieldType): LoginEvent
    data class LoginButtonClicked(val navigateToDashboardScreen: (ArsaStyleRole) -> Unit):
        LoginEvent
}

data class LoginState (
    val userLoginState: LoginSectionState = LoginSectionState(),
    val networkState: ArsaScreenNetworkState = ArsaScreenNetworkState.Idle
)