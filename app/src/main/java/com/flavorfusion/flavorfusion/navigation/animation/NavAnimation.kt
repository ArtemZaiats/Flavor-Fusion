package com.flavorfusion.flavorfusion.navigation.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.ui.NavDisplay.popTransitionSpec
import androidx.navigation3.ui.NavDisplay.predictivePopTransitionSpec
import androidx.navigation3.ui.NavDisplay.transitionSpec

val tabMetadata = transitionSpec {
    fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    ) togetherWith fadeOut(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    )
} + popTransitionSpec {
    fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    ) togetherWith fadeOut(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    )
} + predictivePopTransitionSpec {
    fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    ) togetherWith fadeOut(
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing,
        )
    )
}

val screenMetadata = transitionSpec {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(300)
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(300)
    )
} + popTransitionSpec {
    slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(300)
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(300)
    )
} + predictivePopTransitionSpec {
    slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(300)
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(300)
    )
}