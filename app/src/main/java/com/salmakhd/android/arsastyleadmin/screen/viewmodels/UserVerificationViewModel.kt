package com.salmakhd.android.arsastyleadmin.screen.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.salmakhd.android.arsastyleadmin.common.model.ArsaScreenNetworkState
import com.salmakhd.android.arsastyleadmin.common.utility.ArsaStyleConstants
import com.salmakhd.android.arsastyleadmin.server.ApiServices
import com.salmakhd.android.arsastyleadmin.server.model.SalonCommentDTO
import com.salmakhd.android.arsastyleadmin.server.model.ServiceDTO
import com.salmakhd.android.arsastyleadmin.server.model.StylistDTO
import com.salmakhd.android.arsastyleadmin.server.responseModel.MessageResponse
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaScreen
import com.salmakhd.android.arsastyleadmin.ui.theme.networkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

sealed interface UserVerificationScreenEvent {
    data object StylistsTabSelected: UserVerificationScreenEvent
    data object CommentsTabSelected: UserVerificationScreenEvent
    data object ServicesTabSelected: UserVerificationScreenEvent
    data class StylistSelected(val stylistId: String, val action: UserVerificationAction) : UserVerificationScreenEvent
    data class CommentSelected(val commentId: String, val action: UserVerificationAction) : UserVerificationScreenEvent
    data class ServiceSelected(val serviceId: String, val action: UserVerificationAction) : UserVerificationScreenEvent
}

enum class UserVerificationAction {
    ACCEPT,
    DECLINE,
    MESSAGE
}

@HiltViewModel
class UserVerificationViewModel @Inject constructor(
    private val apiServices: ApiServices
): ViewModel() {
    // operation successful? emit true
    private val _operationChannel = Channel<Boolean>();
    val operationChannel = _operationChannel.receiveAsFlow()

    var stylistsTabState by mutableStateOf(ArsaScreen<List<StylistDTO>>(emptyList()))
    var commentsTabState by mutableStateOf(ArsaScreen<List<SalonCommentDTO>>(emptyList()))
    var servicesTabState by mutableStateOf(ArsaScreen<List<ServiceDTO>>(emptyList()))

    init {
        getComments()
    }

    fun onEvent(event: UserVerificationScreenEvent) {
        when(event) {
            UserVerificationScreenEvent.CommentsTabSelected -> {
                getComments()
            }
            UserVerificationScreenEvent.ServicesTabSelected -> {
                getServices()
            }
            UserVerificationScreenEvent.StylistsTabSelected -> {
                getStylists()
            }
            is UserVerificationScreenEvent.CommentSelected -> {
                when(event.action) {
                    UserVerificationAction.ACCEPT -> {

                    }
                    UserVerificationAction.DECLINE -> {

                    }
                    UserVerificationAction.MESSAGE -> {

                    }
                }
            }
            is UserVerificationScreenEvent.ServiceSelected -> {
                when(event.action) {
                    UserVerificationAction.ACCEPT -> {

                    }
                    UserVerificationAction.DECLINE -> {

                    }
                    UserVerificationAction.MESSAGE -> {

                    }
                }
            }
            is UserVerificationScreenEvent.StylistSelected -> {
                when(event.action) {
                    UserVerificationAction.ACCEPT -> {

                    }
                    UserVerificationAction.DECLINE -> {

                    }
                    UserVerificationAction.MESSAGE -> {

                    }
                }
            }
        }
    }

    private fun getStylists() {
        stylistsTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = null,
            networkState = ArsaScreenNetworkState.Loading
        )

        apiServices.getStylists(
            "aryan_model"
        ).enqueue(object : Callback<MessageResponse<List<StylistDTO>>> {
            override fun onResponse(
                call: Call<MessageResponse<List<StylistDTO>>>,
                response: Response<MessageResponse<List<StylistDTO>>>
            ) {
                if(response.isSuccessful){ // todo: successful -> show welcome message before navigating
                    Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the isSuccussful block now")
                    // save to shared preferences
                    response.body()?.let {response ->
                        stylistsTabState = ArsaScreen(
                            data = response.data,
                            networkState = ArsaScreenNetworkState.Success,
                            errorMessage = null
                        )
                    }
                } else {
                    // todo: lottie animation -> different errors
                    when(response.code()) {
                        406 -> { // wrong input
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateStylistsTabErrorMessage(response.body()?.message)
                        }
                        400 -> { // username or password incorrect
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateStylistsTabErrorMessage(response.body()?.message)
                        }
                        500 -> { // server error
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateStylistsTabErrorMessage(response.body()?.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MessageResponse<List<StylistDTO>>>, t: Throwable) {
                Log.d(ArsaStyleConstants.ARSA_LOG_TAG, t.message.toString())
                stylistsTabState = ArsaScreen(
                    data = emptyList(),
                    errorMessage = null,
                    networkState = ArsaScreenNetworkState.Error(
                        networkError, onRetryButtonClicked = {getStylists()}
                    )
                )
            }
        })
    }

    private fun updateStylistsTabErrorMessage(errorMessage: String?) {
        stylistsTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = errorMessage,
            networkState = ArsaScreenNetworkState.Idle
        )
    }

    private fun getComments() {
        commentsTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = null,
            networkState = ArsaScreenNetworkState.Loading
        )

        apiServices.getComments(
            "aryan_model"
        ).enqueue(object : Callback<MessageResponse<List<SalonCommentDTO>>> {
            override fun onResponse(
                call: Call<MessageResponse<List<SalonCommentDTO>>>,
                response: Response<MessageResponse<List<SalonCommentDTO>>>
            ) {
                if(response.isSuccessful){ // todo: successful -> show welcome message before navigating
                    Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the isSuccussful block now")
                    // save to shared preferences
                    response.body()?.let {response ->
                        commentsTabState = ArsaScreen(
                            data = response.data,
                            networkState = ArsaScreenNetworkState.Success,
                            errorMessage = null
                        )
                    }
                } else {
                    // todo: lottie animation -> different errors
                    when(response.code()) {
                        406 -> { // wrong input
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateCommentsTabErrorMessage(response.body()?.message)
                        }
                        400 -> { // username or password incorrect
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateCommentsTabErrorMessage(response.body()?.message)
                        }
                        500 -> { // server error
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateCommentsTabErrorMessage(response.body()?.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MessageResponse<List<SalonCommentDTO>>>, t: Throwable) {
                Log.d(ArsaStyleConstants.ARSA_LOG_TAG, t.message.toString())
                commentsTabState = ArsaScreen(
                    data = emptyList(),
                    errorMessage = null,
                    networkState = ArsaScreenNetworkState.Error(
                        networkError, onRetryButtonClicked = {getStylists()}
                    )
                )
            }
        })
    }

    private fun updateCommentsTabErrorMessage(errorMessage: String?) {
        commentsTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = errorMessage,
            networkState = ArsaScreenNetworkState.Idle
        )
    }

    private fun getServices() {
        servicesTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = null,
            networkState = ArsaScreenNetworkState.Loading
        )

        apiServices.getServices(
            "aryan_model"
        ).enqueue(object : Callback<MessageResponse<List<ServiceDTO>>> {
            override fun onResponse(
                call: Call<MessageResponse<List<ServiceDTO>>>,
                response: Response<MessageResponse<List<ServiceDTO>>>
            ) {
                if(response.isSuccessful){ // todo: successful -> show welcome message before navigating
                    Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the isSuccussful block now")
                    // save to shared preferences
                    response.body()?.let {response ->
                        servicesTabState = ArsaScreen(
                            data = response.data,
                            networkState = ArsaScreenNetworkState.Success,
                            errorMessage = null
                        )
                    }
                } else {
                    // todo: lottie animation -> different errors
                    when(response.code()) {
                        406 -> { // wrong input
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateServicesTabErrorMessage(response.body()?.message)
                        }
                        400 -> { // username or password incorrect
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateServicesTabErrorMessage(response.body()?.message)
                        }
                        500 -> { // server error
                            Log.d(ArsaStyleConstants.ARSA_LOG_TAG, "within the code block now")
                            updateServicesTabErrorMessage(response.body()?.message)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MessageResponse<List<ServiceDTO>>>, t: Throwable) {
                Log.d(ArsaStyleConstants.ARSA_LOG_TAG, t.message.toString())
                servicesTabState = ArsaScreen(
                    data = emptyList(),
                    errorMessage = null,
                    networkState = ArsaScreenNetworkState.Error(
                        networkError, onRetryButtonClicked = {getStylists()}
                    )
                )
            }

        })
    }

    private fun updateServicesTabErrorMessage(errorMessage: String?) {
        servicesTabState = ArsaScreen(
            data = emptyList(),
            errorMessage = errorMessage,
            networkState = ArsaScreenNetworkState.Idle
        )
    }

    private fun acceptStylist() {

    }
    private fun declineStylist() {

    }
    private fun acceptComment() {

    }
    private fun declineComment() {

    }
    private fun acceptService() {

    }
    private fun declineService() {

    }
}