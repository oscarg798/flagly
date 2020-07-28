package com.oscarg798.flagly.featureflag

interface FeatureFlagProvider {

    fun provideAppSupportedFeatureflags(): Collection<FeatureFlag>
}
