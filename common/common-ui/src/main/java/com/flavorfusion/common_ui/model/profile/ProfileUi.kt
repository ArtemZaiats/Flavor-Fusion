package com.flavorfusion.common_ui.model.profile

import com.flavorfusion.common_domain.model.UserProfile

data class ProfileUi(
    val id: String = "",
    val email: String = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val profileImage: String? = ""
)

fun UserProfile.toUi() = ProfileUi(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    profileImage = avatarUrl
)
