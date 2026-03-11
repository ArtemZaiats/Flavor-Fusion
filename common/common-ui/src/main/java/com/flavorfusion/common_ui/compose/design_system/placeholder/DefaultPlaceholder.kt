package com.flavorfusion.common_ui.compose.design_system.placeholder

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.compose.BlockingLoading
import com.flavorfusion.common_ui.compose.FullScreenProgressIndicator
import com.flavorfusion.common_ui.error.ErrorMessage
import com.flavorfusion.common_ui.theme.FlavorFusionTheme
import com.flavorfusion.common_ui.utils.Constants.ANIMATION_TIME

@Composable
fun DefaultPlaceholder(
    modifier: Modifier = Modifier,
    loading: Boolean,
    errorMessage: ErrorMessage?,
    enterTransition: EnterTransition = fadeIn(animationSpec = tween(ANIMATION_TIME)),
    exitTransition: ExitTransition = fadeOut(animationSpec = tween(ANIMATION_TIME)),
    onRetry: () -> Unit = {},
    content: @Composable BoxScope.(PlaceholderState) -> Unit
) {
    val state = PlaceholderState.fromConditions(loading = loading, hasError = errorMessage != null)

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = state,
            transitionSpec = {
                val finalExitTransition = when (initialState) {
                    PlaceholderState.ERROR -> exitTransition
                    PlaceholderState.LOADING -> exitTransition
                    PlaceholderState.SUCCESS -> ExitTransition.None
                }
                val finalEnterTransition = when (targetState) {
                    PlaceholderState.LOADING,
                    PlaceholderState.ERROR -> EnterTransition.None

                    PlaceholderState.SUCCESS -> enterTransition
                }
                finalEnterTransition togetherWith finalExitTransition
            }
        ) { targetUiState ->
            Box(modifier = Modifier.fillMaxSize()) {
                content(targetUiState)
                when (targetUiState) {
                    PlaceholderState.LOADING -> FullScreenProgressIndicator()
                    PlaceholderState.ERROR -> {
                        ErrorScreen(
                            modifier = modifier,
                            errorTitle = errorMessage?.title,
                            errorDescription = errorMessage?.errorText,
                            onRetry = onRetry
                        )
                        BlockingLoading(isDisplayed = loading)
                    }

                    PlaceholderState.SUCCESS -> {}
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorTitle: String?,
    errorDescription: String?,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = FlavorFusionTheme.colors.backgroundPrimary)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            painter = painterResource(R.drawable.duckplaceholder),
            contentDescription = "Image error placeholder"
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = errorTitle ?: "",
            style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            color = FlavorFusionTheme.colors.contentPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = errorDescription ?: "",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center,
            color = FlavorFusionTheme.colors.contentSecondary
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRetry
        ) {
            Text(text = "Retry")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
@PreviewLightDark
private fun DefaultPlaceholderPreview() {
    val errorMessage = ErrorMessage(
        title = "Error message text",
        errorText = "Please, check your network connection and try connecting again",
        buttonText = "Retry"
    )
    FlavorFusionTheme {
        DefaultPlaceholder(
            loading = false,
            errorMessage = errorMessage,
            onRetry = {},
            content = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Content")
                }
            },
        )
    }
}