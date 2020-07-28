package com.oscarg798.flagly.di

import com.oscarg798.flagly.developeroptions.FeatureFlagActivityContract
import com.oscarg798.flagly.developeroptions.FeatureFlagHandlerPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import com.oscarg798.flagly.utils.CoroutineContextProvider
import javax.inject.Singleton

@Module
object FeatureHandlerModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider() =
        CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)

    @Singleton
    @Provides
    fun providePresenter(featureFlagHandlerPresenter: FeatureFlagHandlerPresenter): FeatureFlagActivityContract.Presenter =
        featureFlagHandlerPresenter
}