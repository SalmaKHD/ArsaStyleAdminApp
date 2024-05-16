package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

@Composable
fun ArsaScreenTitle (
    modifier: Modifier = Modifier,
    screenInstructions: String? = null,
    title: String
) {
    var isScreenInstructionsDialogVisible by remember {
        mutableStateOf(false)
    }
    if(isScreenInstructionsDialogVisible) {
        ArsaDialog(
            title = screenInstructions ?: "",
            text = "",
            onDismiss = { isScreenInstructionsDialogVisible = false },
            onConfirmButtonClicked = {isScreenInstructionsDialogVisible = false},
            onCancelButtonClicked = { isScreenInstructionsDialogVisible = false }
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = modifier,
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        if (screenInstructions != null) {
                IconButton(
                    modifier = Modifier
                        .wrapContentSize(),
                    onClick = { isScreenInstructionsDialogVisible = true }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_question_circle),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
        }
    }
}

@AllSizeDevicePreview
@Composable
fun ArsaScreenTitlePreview() {
    ArsaStyleAdminTheme {
        ArsaScreenTitle(title = "خاموش کردن حساب", screenInstructions = "خاموشی حساب برای ...")
    }
}
