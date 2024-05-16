package com.salmakhd.android.arsastyleadmin.ui.designsystem

import com.salmakhd.android.arsastyleadmin.common.model.ArsaScreenNetworkState

data class ArsaScreen <T> (
    val data: T,
    val errorMessage: String? = null,
    val networkState: ArsaScreenNetworkState = ArsaScreenNetworkState.Idle,
    var isUiReady: Boolean = false
)