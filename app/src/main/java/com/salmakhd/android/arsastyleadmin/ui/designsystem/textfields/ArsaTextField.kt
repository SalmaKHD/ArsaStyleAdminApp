package com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ContactSupport
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.ui.designsystem.ArsaDialog
import com.salmakhd.android.arsastyleadmin.ui.designsystem.SmallDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen
import com.salmakhd.android.arsastyleadmin.ui.theme.hintTitle

@Composable
fun ArsaTextField(
    modifier: Modifier = Modifier,
    value: String? = null,
    label: String,
    errorMessage: String? = null,
    hint: String? = null,
    isValidationIconVisible: Boolean = true,
    imageVector: ImageVector,
    keyBoardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit = {},
    onIconClicked: ()-> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    onTextFieldClicked: (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeHolder: String? = null
) {
    var isHintDialogVisible by remember {
        mutableStateOf(false)
    }
    if(hint != null && isHintDialogVisible) {
        ArsaDialog(
            title = hintTitle,
            text = hint,
            onDismiss = {isHintDialogVisible = false}
        )
    }
    Column(
        modifier =modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val textFiledBorderColor = MaterialTheme.colorScheme.tertiary
        val TextFieldmodifier = remember {
            if(onTextFieldClicked!=null) {
                Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
                        color = textFiledBorderColor
                    )
                    .clickable {
                        onTextFieldClicked()
                    }
            } else {
                Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
                        color = textFiledBorderColor
                    )
            }
        }
        TextField(
            modifier = TextFieldmodifier,
            value = value ?: "",
            onValueChange = { onValueChanged(it) },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
            label = {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            placeholder = {
                Text(
                    text = placeHolder ?: "",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            trailingIcon = {
                if (trailingIcon == null) {
                    Row(modifier = Modifier.padding(MaterialTheme.dimen.arsaPadding2)) {
                        if (isValidationIconVisible && value != null) {
                            Icon(
                                modifier = Modifier.clickable { onIconClicked() },
                                tint =
                                if (errorMessage != null) {
                                    MaterialTheme.colorScheme.error
                                } else Color(
                                    0xFF009688
                                ),
                                imageVector =
                                if (errorMessage != null) {
                                    Icons.Filled.Close
                                } else Icons.Filled.Check,
                                contentDescription = "",
                            )
                        }
                        if (hint != null) {
                            Icon(
                                modifier = Modifier.clickable {
                                    isHintDialogVisible = true
                                },
                                tint = MaterialTheme.colorScheme.error,
                                imageVector = Icons.Outlined.ContactSupport ,
                                contentDescription = "",
                            )
                        }
                    }
                } else {
                    trailingIcon()
                }
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable { onIconClicked() },
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = imageVector,
                    contentDescription = "",
                )
            },
            shape = RoundedCornerShape(10.dp,10.dp,0.dp,0.dp),
            maxLines = 1,
            keyboardOptions = keyBoardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            isError = errorMessage!=null,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        if(errorMessage != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
@SmallDevicePreview
fun ArsaTextFieldComposable() {
    ArsaStyleAdminTheme {
        ArsaTextField(value = null, label = "نام", imageVector = Icons.Filled.AccountBox , hint = "fdfdfdfd")
    }
}