package com.salmakhd.android.arsastyleadmin.common.model

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null
)