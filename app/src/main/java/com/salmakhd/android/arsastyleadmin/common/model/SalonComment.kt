package com.salmakhd.android.arsastyleadmin.common.model

data class ServiceRating(
    val service: String,
    val rating: Float
)
data class SalonComment(
    val commentId: String = "0",
    val salonUsername: String = "0",
    val customerUsername: String = "0",
    val overallRating: Float,
    val comment: String,
    val serviceRatings: List<ServiceRating> = emptyList()
)