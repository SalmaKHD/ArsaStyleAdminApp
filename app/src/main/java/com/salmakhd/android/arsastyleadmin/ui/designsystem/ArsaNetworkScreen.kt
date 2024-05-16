package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salmakhd.android.arsastyleadmin.common.model.ArsaScreenNetworkState
import com.salmakhd.android.arsastyleadmin.R
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme
import com.salmakhd.android.arsastyleadmin.ui.theme.dimen

@Composable
fun ArsaNetworkScreen(
    modifier: Modifier = Modifier,
    networkState: ArsaScreenNetworkState,
    successContent: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Card(modifier = Modifier
            .wrapContentSize(),
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp
        ) {
            when(networkState) {
                is ArsaScreenNetworkState.Loading -> {
                    ArsaInfiniteLottieAnimation(
                        animationRes = R.raw.anim_loading_home
                    )
                }
                is ArsaScreenNetworkState.Success -> {
                    successContent()
                }
                is ArsaScreenNetworkState.Idle -> {}
                is ArsaScreenNetworkState.Error -> {
                    Row(
                        modifier = Modifier
                            .padding(MaterialTheme.dimen.arsaPadding4),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = networkState.onRetryButtonClicked) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                modifier = Modifier,
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = "تلاش دوباره",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@SmallDevicePreview
@Composable
fun NetworkScreenPreview() {
    ArsaStyleAdminTheme {
        ArsaNetworkScreen(
            networkState = ArsaScreenNetworkState.Error(
                "",
                onRetryButtonClicked = {})
        )
    }
}

/**
 * related to network connection only -> nothing to do with the response
 */
@Composable
fun ArsaNetworkResponseRow(
    modifier: Modifier = Modifier,
    networkState: ArsaScreenNetworkState,
    readyStateContent: @Composable () -> Unit = {}
) {
    when (networkState) {
        is ArsaScreenNetworkState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        is ArsaScreenNetworkState.Error -> {
            ArsaNetworkErrorResponseRow(networkState = networkState, modifier = modifier)
        }

        is ArsaScreenNetworkState.Success -> {
            readyStateContent()
        }
        else -> {}
    }
}

@Composable
fun ArsaNetworkErrorResponseRow(
    modifier: Modifier = Modifier,
    networkState: ArsaScreenNetworkState.Error,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {networkState.onRetryButtonClicked()}) {
            Icon(
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Filled.Refresh,
                contentDescription = null
            )
        }

        Text(
            text = "تلاش دوباره",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}