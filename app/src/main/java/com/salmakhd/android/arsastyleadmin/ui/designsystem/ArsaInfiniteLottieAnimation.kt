package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.salmakhd.android.arsastyleadmin.R

@Composable
fun ArsaInfiniteLottieAnimation(
    modifier: Modifier = Modifier,
    contentScale: ContentScale= ContentScale.Fit,
    @RawRes animationRes: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    LottieAnimation(
        modifier = modifier,
        alignment = Alignment.BottomCenter,
        composition = composition,
        contentScale = contentScale,
        progress = {progress},

    )
}

@SmallDevicePreview
@Composable
fun ArsaInfiniteLottieAnimationPreview() {
    ArsaInfiniteLottieAnimation(
        modifier = Modifier
            .fillMaxWidth(),
        animationRes = R.raw.animation_login,
    )
}