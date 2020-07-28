package com.oscarg798.flagly.featureflag

import com.oscarg798.flagy.exceptions.FeatureFlagNotPresentInHandlerException

interface DynamicFeatureFlagHandler : FeatureFlagHandler {

    @Throws(FeatureFlagNotPresentInHandlerException::class)
    override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean

    fun setValue(featureFlag: FeatureFlag, value: Boolean)
}