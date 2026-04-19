package com.flavorfusion.common_domain.model

data class UserProfile(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String
)
