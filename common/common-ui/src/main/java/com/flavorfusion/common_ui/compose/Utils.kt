package com.flavorfusion.common_ui.compose

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.flavorfusion.common_ui.R
import com.flavorfusion.core_ui.mvi.CommonEffect
import com.flavorfusion.core_ui.mvi.MviViewModel
import com.flavorfusion.core_ui.mvi.UiEffect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun EffectHandler(
    viewModel: MviViewModel<*, *>,
    onHandleEffect: ((UiEffect) -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.effect
                    .onEach { effect ->
                        when (effect) {
                            is CommonEffect.Toast -> Toast.makeText(
                                context,
                                effect.message,
                                Toast.LENGTH_SHORT
                            ).show()

                            is CommonEffect.CopyToClipboard -> copyToClipboard(context, effect.text)
                            is CommonEffect.HideKeyboard -> focusManager.clearFocus(force = true)
                            is CommonEffect.OpenUrl -> openUrl(context, effect.url)
                            else -> onHandleEffect?.invoke(effect)
                        }
                    }
                    .launchIn(scope)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

private fun copyToClipboard(
    context: Context,
    text: String
) {
    val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText(null, text))
}

private fun openUrl(
    context: Context,
    url: String
) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = url.toUri()
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "No app was found capable of opening the URL!",
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun coilImageRequest(context: Context, data: Any?) = remember(data) {
    ImageRequest.Builder(context)
        .data(data)
        .crossfade(true)
        .placeholder(R.drawable.cocktail_placeholder)
        .error(R.drawable.cocktail_placeholder)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
}

/**
 * Extension function to extract YouTube Video ID from a URL.
 * Supports:
 * - https://www.youtube.com/watch?v=VIDEO_ID
 * - https://youtu.be/VIDEO_ID
 * - https://www.youtube.com/embed/VIDEO_ID
 */
fun String.extractYoutubeVideoId(): String {
    val regex =
        "^(?:https?:\\/\\/)?(?:www\\.|m\\.)?(?:youtube\\.com\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)|youtu\\.be\\/)([\\w\\-]{11})(?:\\S+)?$"
    val pattern = java.util.regex.Pattern.compile(regex, java.util.regex.Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)

    return if (matcher.find()) {
        matcher.group(1)
    } else {
        ""
    }
}