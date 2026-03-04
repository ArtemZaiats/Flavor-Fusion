package com.flavorfusion.common_ui.compose.design_system.pull_to_refresh

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flavorfusion.common_ui.theme.FlavorFusionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxWithPullToRefresh(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val state = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        state = state,
        onRefresh = onRefresh,
        content = content
    )
}