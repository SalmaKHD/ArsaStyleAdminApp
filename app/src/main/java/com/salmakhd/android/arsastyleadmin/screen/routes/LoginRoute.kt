package com.salmakhd.android.arsastyleadmin.screen.routes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.salmakhd.android.arsastyleadmin.common.model.ArsaFieldType
import com.salmakhd.android.arsastyleadmin.screen.viewmodels.LoginEvent
import com.salmakhd.android.arsastyleadmin.screen.viewmodels.LoginViewModel
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaButton
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaButtonStyle
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaInfiniteLottieAnimation
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaNetworkResponseRow
import com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields.ArsaPasswordTextField
import com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields.ArsaTextField
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.arsaStyle
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen
import com.salmakhd.android.arsastyleadmin.ui.theme.login
import com.salmakhd.android.arsastyleadmin.ui.theme.passwordFieldLabel
import com.salmakhd.android.arsastyleadmin.ui.theme.signup
import com.salmakhd.android.arsastyleadmin.ui.theme.userNameFieldLabel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToDashboardScreen: () -> Unit = {},
    navigateToSignUpRoute: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArsaInfiniteLottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            animationRes = R.raw.anim_login,

        )
        LoginSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimen.arsaPadding7),
            loginSectionState = viewModel.uiState.userLoginState,
            onUsernameValueChanged = {
                viewModel.onEvent(
                    LoginEvent.FieldValueChanged(
                        it,
                        ArsaFieldType.USERNAME
                    )
                )
            },
            onPasswordValueChanged = {
                viewModel.onEvent(
                    LoginEvent.FieldValueChanged(
                        it,
                        ArsaFieldType.PASSWORD
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding2))

        ArsaNetworkResponseRow(networkState = viewModel.uiState.networkState)

        ArsaButton(
            modifier = Modifier.fillMaxWidth(0.6f),
            onButtonClicked = {
                viewModel.onEvent(
                    LoginEvent.LoginButtonClicked { navigateToDashboardScreen() }
                )
            },
            text = login,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding2))
        ArsaButton(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = signup,
            onButtonClicked = { navigateToSignUpRoute() },
            buttonStyle = ArsaButtonStyle.OUTLINED
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding1))
        ForgotPassword(
            onForgotPasswordClicked = {
                // TODO: WHAT SHOULD HAPPEN NEXT?
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        AppName()
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding1))
    }
}

@Composable
fun AppName(
    modifier: Modifier = Modifier
) {
    Text(
        text = arsaStyle,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}
@Composable
fun LoginSection(
    modifier: Modifier = Modifier,
    loginSectionState: LoginSectionState,
    onUsernameValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // username
        ArsaTextField(
            value = loginSectionState.userName.value,
            label = userNameFieldLabel,
            isValidationIconVisible = false,
            imageVector = Icons.Filled.AccountBox,
            onValueChanged = {onUsernameValueChanged(it)},
            errorMessage = loginSectionState.userName.errorMessage
        )
        Spacer(modifier = Modifier
            .height(MaterialTheme.dimen.arsaPadding5)
        )
        // password
        ArsaPasswordTextField(
            value = loginSectionState.password.value,
            errorMessage = loginSectionState.password.errorMessage,
            hintVisible = false,
            isIconVisible = false,
            label = passwordFieldLabel,
            onValueChanged = {onPasswordValueChanged(it)}
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding2))
        if(loginSectionState.loginErrorMessage != null) {
            ArsaErrorMessageText(text = loginSectionState.loginErrorMessage)
            Spacer(modifier = Modifier.height(MaterialTheme.dimen.arsaPadding1))
        }
    }
}

@Composable
fun ForgotPassword(
    modifier: Modifier = Modifier,
    onForgotPasswordClicked: () -> Unit = {}
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier,
            text ="رمز عبورم را",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "فراموش کرده ام",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimen.smallPadding)
                .clickable { onForgotPasswordClicked() },
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            color= Color(0xFF009688)
        )
    }
}
@Composable
fun SignUpText(
    modifier: Modifier = Modifier,
    onSignUpTextClicked: () -> Unit = {}
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier,
            text = "حساب ندارید؟",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )

        Text(
            text = "ثبت نام",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimen.smallPadding)
                .clickable { onSignUpTextClicked() },
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "کنید",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ArsaErrorMessageText(
    text: String,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error
    )
}

/*
Previews
 */
@Preview
@Composable
fun SignUpTextPreview() {
    SignUpText()
}

//@Preview
@Composable
fun LoginSectionPreview() {
    ArsaStyleAdminTheme {
        LoginSectionPreview()
    }
}