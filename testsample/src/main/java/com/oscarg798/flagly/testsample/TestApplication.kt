package com.oscarg798.flagly.testsample

import android.app.Application
import com.oscarg798.flagly.developeroptions.FeatureHandleResourceProvider
import com.oscarg798.flagly.featureflag.DynamicFeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlag
import com.oscarg798.flagly.featureflag.FeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagProvider

class TestApplication : Application(), FeatureHandleResourceProvider {

    private val featureFlagHandler = object : FeatureFlagHandler, DynamicFeatureFlagHandler {
        private val featureflagMap = HashMap<String, Boolean>()

        override fun setValue(featureFlag: FeatureFlag, value: Boolean) {
            featureflagMap[featureFlag.name] = value
        }

        override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean {
            return featureflagMap[featureFlag.name] ?: false
        }

        override fun isValueOverriden(featureFlag: FeatureFlag): Boolean =
            featureflagMap.containsKey(featureFlag.name)
        
        override fun removeOverridenValue(featureFlag: FeatureFlag) {
            featureflagMap.remove(featureFlag.name)
        }
    }

    override fun getFeatureFlagProvider(): FeatureFlagProvider = object : FeatureFlagProvider {

        override fun provideAppSupportedFeatureflags(): Collection<FeatureFlag> {
            return setOf(FeatureFlagOne, FeatureFlagTwo, FeatureFlagThree, FeatureFlagFour)
        }
    }

    override fun getRemoteFeatureFlagHandler(): FeatureFlagHandler = object : FeatureFlagHandler {
        override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean {
            return featureFlag is FeatureFlagOne || featureFlag is FeatureFlagThree
        }
    }

    override fun getLocalFeatureflagHandler(): DynamicFeatureFlagHandler = featureFlagHandler
}
