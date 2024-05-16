package com.salmakhd.android.arsastyleadmin.common.model

// for network-related states only -> don't combine with screen-level error messages
sealed interface ArsaScreenNetworkState {
    data object Idle: ArsaScreenNetworkState
    data object Loading: ArsaScreenNetworkState
    data object Success: ArsaScreenNetworkState
    data class Error(val errorMessage: String, val onRetryButtonClicked: () -> Unit = {}):
        ArsaScreenNetworkState
}