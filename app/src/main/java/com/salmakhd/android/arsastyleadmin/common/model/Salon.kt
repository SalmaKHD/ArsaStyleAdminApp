package com.salmakhd.android.arsastyleadmin.common.model

import android.location.Location
import com.salmakhd.android.arsastyleadmin.screen.routes.Gender
import java.time.LocalDate

data class Address(
    val city: String,
    val state: String
)

data class Salon(
    val name: String = "",
    val location: Location? = null,
    val address: String = "",
    val imageURL: String? = null,
    val city: City? = null,
    val tags: List<ArsaTag> = emptyList(),
    val rating: Float = 0.0f, // OUT of 5 stars,
    val registryDate: LocalDate? = null,
    val gender: Gender = Gender.Male
)