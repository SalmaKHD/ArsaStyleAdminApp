package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen

@Composable
fun ArsaDialog(
    title: String,
    text: String,
    dialogOpacity: Float = 0.8f,
    onDismiss: () -> Unit = {},
    confirmButtonText: String? = null,
    onConfirmButtonClicked: () -> Unit = {},
    cancelButtonText: String? = null,
    onCancelButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .fillMaxWidth(0.8f),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary
            ),
            color = MaterialTheme.colorScheme.primary.copy(alpha = dialogOpacity)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimen.arsaPadding3),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // define title
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )

                // define text
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                )

                // define buttons
                if(confirmButtonText!=null && cancelButtonText!=null) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        // define confirmation button
                        Text(
                            text = confirmButtonText,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    onClick = { onConfirmButtonClicked() }
                                )
                        )

                        // define dismiss button
                        Text(
                            text = cancelButtonText,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    onClick = { onCancelButtonClicked() }
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NirvanaConfirmationDialogPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ArsaStyleAdminTheme {
            ArsaDialog(
                dialogOpacity = 0.5f,
                modifier = Modifier.fillMaxWidth(0.9f),
                title = "",
                text = "ایا مطمینید؟",
                confirmButtonText = "تغییر",
                cancelButtonText = "خیر",
            )
        }
    }
}