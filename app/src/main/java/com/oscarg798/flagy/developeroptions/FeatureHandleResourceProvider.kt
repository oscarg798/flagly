package com.oscarg798.flagly.developeroptions

import com.oscarg798.flagly.featureflag.DynamicFeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagProvider

interface FeatureHandleResourceProvider {

    fun  getFeatureFlagProvider() :  FeatureFlagProvider
    fun  getRemoteFeatureFlagHandler(): FeatureFlagHandler
    fun  getLocalFeatureflagHandler(): DynamicFeatureFlagHandler
}