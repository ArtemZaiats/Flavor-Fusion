package com.flavorfusion.common_ui.compose.design_system.placeholder

enum class PlaceholderState {
    LOADING,
    SUCCESS,
    ERROR;

    companion object {
        fun fromConditions(loading: Boolean, hasError: Boolean): PlaceholderState {
            return when {
                hasError -> ERROR
                loading -> LOADING
                else -> SUCCESS
            }
        }
    }
}