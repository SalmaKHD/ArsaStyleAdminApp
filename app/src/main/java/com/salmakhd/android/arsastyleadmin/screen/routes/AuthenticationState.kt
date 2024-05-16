package com.salmakhd.android.arsastyleadmin.screen.routes

import android.util.Log
import com.salmakhd.android.arsastyleadmin.common.model.ArsaScreenNetworkState
import com.salmakhd.android.arsastyleadmin.common.model.ArsaStyleRole
import com.salmakhd.android.arsastyleadmin.common.model.PermissionResult
import com.salmakhd.android.arsastyleadmin.common.model.ArsaOperationStatus
import com.salmakhd.android.arsastyleadmin.common.model.City
import com.salmakhd.android.arsastyleadmin.common.model.Province
import com.salmakhd.android.arsastyleadmin.ui.theme.female
import com.salmakhd.android.arsastyleadmin.ui.theme.male
import java.time.LocalDate

data class LoginSectionState(
    val userName: ArsaFieldState = ArsaFieldState(),
    val password: ArsaFieldState = ArsaFieldState(),
    val loginErrorMessage: String? = null
)

data class BasicUserSignUpState(
    val firstName: ArsaFieldState = ArsaFieldState(),
    val lastName: ArsaFieldState = ArsaFieldState(),
    val userName: ArsaFieldState = ArsaFieldState(),
    val password: ArsaFieldState = ArsaFieldState(),
    val passwordRepeated: ArsaFieldState = ArsaFieldState(),
    val nationalCodeField: ArsaFieldState = ArsaFieldState(),
    val invitationCode: ArsaFieldState = ArsaFieldState(),
    val salonName: ArsaFieldState = ArsaFieldState(),
    val salonAddress: ArsaFieldState = ArsaFieldState(),
    val phoneNumber: ArsaFieldState = ArsaFieldState(),
    val code: ArsaFieldState = ArsaFieldState(),
    val numberOfAttempts: Int = 0,
    val gender: Gender = Gender.Male,
    val city: City? = null,
    val province: Province? = null,
    val isCodeFieldVisible: Boolean = false,
    val isInfoSectionVisible: Boolean = false,
    val isCodeCorrect: Boolean? = null,
    val isNavigationAllowed: Boolean = false,
    val shouldButtonBeEnabled: Boolean = true,
    val networkState: ArsaScreenNetworkState = ArsaScreenNetworkState.Idle,
    val errorMessage: String? = null
) {
    fun shouldButtonBeEnabled(role: ArsaStyleRole): Boolean {
        val isInputValid =
            firstName.isFieldInputValid() &&
                lastName.isFieldInputValid() &&
                userName.isFieldInputValid() &&
                nationalCodeField.isFieldInputValid() &&
                password.isFieldInputValid() &&
                passwordRepeated.isFieldInputValid() &&
                phoneNumber.isFieldInputValid() &&
                code.isFieldInputValid() &&
                invitationCode.errorMessage == null &&
                    city != null && province != null
        
        Log.i("ArsaStyle", "button is enabled: $isInputValid")
        // add extra validation for stylist extra fields
        if(role == ArsaStyleRole.STYLIST) return isInputValid && salonName.isFieldInputValid() && salonAddress.isFieldInputValid()
        else return isInputValid
    }
}

data class StylistSignUpState (
    val basicUserData: BasicUserSignUpState = BasicUserSignUpState(),
    val birthDate: LocalDate? = null,
    val isBirthDatePickerVisible: Boolean = false,
    val shouldShowFaceImageView: Boolean = false,
    val shouldShowIMageCaptureView: Boolean = false,
    val locationOperationState: ArsaOperationStatus = ArsaOperationStatus.UNKNOWN,
    val faceImageOperationState: ArsaOperationStatus = ArsaOperationStatus.UNKNOWN,
    val documentImageOperationStatus: ArsaOperationStatus = ArsaOperationStatus.UNKNOWN,
    val profilePictureOperationStatus : ArsaOperationStatus = ArsaOperationStatus.UNKNOWN,
    val cameraPermissionResult : PermissionResult = PermissionResult.UNKNOWN,
    val shouldShowPermissionDialog: Boolean = false,
    val shouldRegisterButtonBeEnabled: Boolean = true
) {
    fun shouldButtonBeEnabled() : Boolean {
        val validation = basicUserData.salonName.isFieldInputValid() &&
               birthDate!= null&&
                locationOperationState == ArsaOperationStatus.SUCCESS &&
                faceImageOperationState == ArsaOperationStatus.SUCCESS &&
                documentImageOperationStatus == ArsaOperationStatus.SUCCESS &&
                profilePictureOperationStatus == ArsaOperationStatus.SUCCESS
     //   Log.i("NIRVANA", "should button be enabled: $validation, date:${dateOfBirthField.value != null}")
        return validation
    }
}

data class ArsaFieldState(
    val value: String? = null,
    val errorMessage: String? = null
) {
    fun isFieldInputValid() = value!=null&& errorMessage==null
}

enum class Gender(val displayName: String) {
    Female(female),
    Male(male)
}
fun String.asGender() = Gender.valueOf(this)

