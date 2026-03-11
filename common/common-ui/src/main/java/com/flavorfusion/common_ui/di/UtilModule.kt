package com.flavorfusion.common_ui.di

import android.content.Context
import com.flavorfusion.common_ui.DefaultExecutor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.common_ui.error.DefaultErrorMessageProvider
import com.flavorfusion.common_ui.error.DefaultMessageExtractor
import com.flavorfusion.common_ui.error.ErrorMessageExtractor
import com.flavorfusion.common_ui.error.ErrorMessageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun providesErrorMessageExtractor(@ApplicationContext context: Context): ErrorMessageExtractor {
        return DefaultMessageExtractor(context)
    }

    @Provides
    @Singleton
    fun providesExecutor(
        errorMessageExtractor: ErrorMessageExtractor,
        errorMessageProvider: ErrorMessageProvider
    ): Executor {
        return DefaultExecutor(errorMessageExtractor, errorMessageProvider)
    }

    @Provides
    @Singleton
    fun providesErrorMessageProvider(): ErrorMessageProvider {
        return DefaultErrorMessageProvider()
    }
}