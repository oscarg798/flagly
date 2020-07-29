package com.oscarg798.flagly.developeroptions.adapter

import com.oscarg798.flagly.featureflag.FeatureFlag


interface FeatureFlagValueChangedListener {

    fun onFeatureFlagValueChanged(featureFlag: FeatureFlag, value: Boolean)

    fun onOverrideValueChange(featureFlag: FeatureFlag, override: Boolean)
}