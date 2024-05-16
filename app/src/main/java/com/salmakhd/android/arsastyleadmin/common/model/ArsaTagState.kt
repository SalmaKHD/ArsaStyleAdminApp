package com.salmakhd.android.arsastyleadmin.common.model

data class ArsaTagState(
    val label: String,
    val selectedTags: List<String> = emptyList(),
    val onClick: () -> Unit = {}
)
