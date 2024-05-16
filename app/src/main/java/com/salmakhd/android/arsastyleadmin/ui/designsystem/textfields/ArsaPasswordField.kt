package com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.salmakhd.android.arsastyleadmin.ui.theme.passwordHint

@Composable
fun ArsaPasswordTextField(
    modifier: Modifier = Modifier,
    isIconVisible: Boolean = true,
    value: String?,
    label: String,
    hintVisible: Boolean = true,
    errorMessage: String? = null,
    onValueChanged: (String) -> Unit = {}
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    ArsaTextField(
        modifier = modifier,
        value = value,
        label = label,
        isValidationIconVisible = isIconVisible,
        hint=  if(hintVisible)passwordHint else null,
        imageVector = Icons.Rounded.RemoveRedEye,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onIconClicked = {isPasswordVisible=!isPasswordVisible },
        onValueChanged = {onValueChanged(it)},
        errorMessage = errorMessage
    )
}