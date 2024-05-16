package com.salmakhd.android.arsastyleadmin.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ArsaSmallShape = RoundedCornerShape(5.dp)
val ArsaMediumShape = RoundedCornerShape(10.dp)
val ArsaLargeShape = RoundedCornerShape(20.dp)
val ArsaExtraLargeShape = RoundedCornerShape(40.dp)

val ArsaShapes = Shapes(
    small = ArsaSmallShape,
    medium = ArsaMediumShape,
    large = ArsaLargeShape,
    extraLarge = ArsaExtraLargeShape
)