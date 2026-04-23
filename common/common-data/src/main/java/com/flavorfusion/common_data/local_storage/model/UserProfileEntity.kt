package com.flavorfusion.common_data.local_storage.model

import com.flavorfusion.common_domain.model.UserProfile
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileEntity(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String
)

fun UserProfile.toEntity() = UserProfileEntity(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatarUrl = avatarUrl
)

fun UserProfileEntity.toDomain() = UserProfile(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatarUrl = avatarUrl
)