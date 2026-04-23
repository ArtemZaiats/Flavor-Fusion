package com.flavorfusion.settings.providers

import android.content.Context
import com.flavorfusion.common_ui.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LegalUrlsProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    val privacyPolicyUrl: String
        get() = context.getString(R.string.feature_settings_privacy_policy_url)

    val termsOfUseUrl: String
        get() = context.getString(R.string.feature_settings_terms_of_use_url)
}
