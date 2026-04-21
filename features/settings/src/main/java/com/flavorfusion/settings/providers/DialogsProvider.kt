package com.flavorfusion.settings.providers

import android.content.Context
import com.flavorfusion.common_ui.R
import com.flavorfusion.common_ui.utils.DataProvider
import com.flavorfusion.settings.model.DialogData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LogOutDataProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : DataProvider<DialogData> {
    override fun provideData(): DialogData {
        return DialogData(
            title = context.getString(R.string.feature_settings_dialog_logout_title),
            message = "",
            confirmButtonText = context.getString(R.string.feature_settings_dialog_logout_confirm),
            cancelButtonText = context.getString(R.string.feature_settings_dialog_logout_cancel)
        )
    }
}

class DeleteAccountDataProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : DataProvider<DialogData> {
    override fun provideData(): DialogData {
        return DialogData(
            title = context.getString(R.string.feature_settings_dialog_delete_account_title),
            message = context.getString(R.string.feature_settings_dialog_delete_account_message),
            confirmButtonText = context.getString(R.string.feature_settings_dialog_delete_account_confirm),
            cancelButtonText = context.getString(R.string.feature_settings_dialog_delete_account_cancel)
        )
    }
}