package com.salmakhd.android.arsastyleadmin.ui.designsystem.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.ui.designsystem.SmallDevicePreview
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArsaSearchField(
    modifier: Modifier = Modifier,
    value: String? = null,
    onFieldValueChanged: (String) -> Unit = {},
    clearButtonVisible: Boolean = false,
    onClearButtonClicked: () -> Unit = {}
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(clearButtonVisible) {
        focusManager.clearFocus(true)
    }

    OutlinedTextField(
        value = value ?: "",
        onValueChange = {
            onFieldValueChanged(it)
        },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        leadingIcon =
        if(value == null) {
            {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = ""
                )
            }
        } else {null},
        textStyle = MaterialTheme.typography.titleMedium,
        keyboardActions = KeyboardActions(
            onDone = {
                keyBoardController?.hide()
            }
        ),
        trailingIcon =
        if(clearButtonVisible) {
            {
                IconButton(onClick = { onClearButtonClicked()}) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            }
        } else {null},
    )
}

@Composable
@SmallDevicePreview
fun NirvanaSearchFieldPreview() {
    ArsaStyleAdminTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ArsaSearchField(modifier = Modifier.fillMaxWidth(), clearButtonVisible =true)
        }
    }
}
