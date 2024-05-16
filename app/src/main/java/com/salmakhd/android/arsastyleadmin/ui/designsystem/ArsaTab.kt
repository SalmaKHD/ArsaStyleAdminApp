package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ArsaTab(
    arsaTabState: ArsaTabState = ArsaTabState(),
    text: String,
    isCardSelected: Boolean,
    onCardClicked: () -> Unit,
    modifier: Modifier =  Modifier,
) {
    // only to add inner shadow to the card
    val brush =
        Brush.verticalGradient(
            0.0f to  if(isCardSelected)  Color(0xFFD9D9D9) else Color.Gray,
            0.2f to
                    if(isCardSelected) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.tertiary.copy(alpha=0.7f)
        )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClicked() },
        elevation = if(isCardSelected) 0.dp else 10.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(brush, shape = MaterialTheme.shapes.small)
            .clip(MaterialTheme.shapes.small)
        ) {
//            Icon(
//               painter = painterResource(id = arsaTabState.iconRes),
//                contentDescription = null,
//                modifier = Modifier.size(24.dp),
//                tint = MaterialTheme.colorScheme.primary
//            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}