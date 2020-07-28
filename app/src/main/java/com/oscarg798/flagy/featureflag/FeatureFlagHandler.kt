package com.oscarg798.flagly.featureflag

interface  FeatureFlagHandler {

    fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean
}