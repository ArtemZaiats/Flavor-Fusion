package com.flavorfusion.common_ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.flavorfusion.common_ui.utils.Constants

@Composable
fun AdaptiveListDetailLayout(
    navigateToDetails: (String) -> Unit,
    emptyDetailText: String,
    listContent: @Composable (onItemSelected: (String) -> Unit) -> Unit,
    detailContent: @Composable (selectedId: String, onBack: () -> Unit) -> Unit
) {
    val isLargeScreen = LocalConfiguration.current.screenWidthDp >= Constants.LARGE_SCREEN_WIDTH_DP

    if (isLargeScreen) {
        var selectedId by rememberSaveable { mutableStateOf<String?>(null) }

        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
            ) {
                listContent { selectedId = it }
            }

            VerticalDivider()

            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
            ) {
                val id = selectedId
                if (id != null) {
                    detailContent(id) { selectedId = null }
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(emptyDetailText)
                    }
                }
            }
        }
    } else {
        listContent(navigateToDetails)
    }
}
