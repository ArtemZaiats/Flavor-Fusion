package com.zaiatsdevelopment.common_data.local_storage.model

import com.flavorfusion.common_domain.model.app_theme.AppTheme
import com.flavorfusion.common_domain.model.app_theme.ThemeType
import kotlinx.serialization.Serializable

@Serializable
data class AppThemeEntity(
    val title: String,
    val type: ThemeType
)

fun AppTheme.toEntity() = AppThemeEntity(
    title = title,
    type = type
)

fun AppThemeEntity.toDomain() = AppTheme(
    title = title,
    type = type
)