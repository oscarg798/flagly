package com.oscarg798.flagly.di

import dagger.BindsInstance
import dagger.Component
import com.oscarg798.flagly.developeroptions.FeatureFlagHandlerActivity
import com.oscarg798.flagly.featureflag.DynamicFeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureHandlerModule::class])
interface FeatureHandlerComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            featureFlagProvider: FeatureFlagProvider,
            @BindsInstance
            remoteFeatureFlagHandler: FeatureFlagHandler,
            @BindsInstance
            localFeatureflagHandler: DynamicFeatureFlagHandler
        ): FeatureHandlerComponent
    }

    fun inject(featureFlagHandlerActivity: FeatureFlagHandlerActivity)
}