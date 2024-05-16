package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen

enum class ArsaButtonStyle {
    OUTLINED,
    FILLED
}

@Composable
fun ArsaButton(
    modifier: Modifier = Modifier,
    text: String,
    textPadding: Dp = MaterialTheme.dimen.smallPadding,
    shape: CornerBasedShape = MaterialTheme.shapes.large,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    buttonStyle: ArsaButtonStyle = ArsaButtonStyle.FILLED,
    enabled: Boolean = true,
    onButtonClicked: () -> Unit = {},
    buttonContent: @Composable () -> Unit = {
        Text(
            modifier=Modifier.padding(textPadding),
            text = text,
            color = Color.Black,
            style = style,
            textAlign = TextAlign.Center
        )
    }
) {
    TextButton(
        modifier = modifier,
        onClick = {onButtonClicked()},
        colors = ButtonDefaults.textButtonColors(
            containerColor = if(buttonStyle == ArsaButtonStyle.FILLED) MaterialTheme.colorScheme.primary else Color.Transparent,
            disabledContainerColor = if(buttonStyle == ArsaButtonStyle.FILLED) MaterialTheme.colorScheme.primary.copy(alpha=0.3f) else Color.Transparent

        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
        shape = shape,
        enabled = enabled
    ){
        buttonContent()
    }
}

@Preview
@Composable
fun ArsaButtonPreview() {
    ArsaStyleAdminTheme {
        ArsaButton(text = "ثبت نام", enabled = false)
    }
}