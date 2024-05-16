package com.salmakhd.android.arsastyleadmin.common.model

import java.time.Instant

data class NotificationResource (
    val title: String,
    val text: String,
    val timeStamp: Instant,
    val seen: Boolean,
    val deepLinkUri: String? = null
)

