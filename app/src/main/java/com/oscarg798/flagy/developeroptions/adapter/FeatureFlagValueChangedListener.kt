package com.oscarg798.flagly.developeroptions.adapter

import com.oscarg798.flagly.featureflag.FeatureFlag


interface FeatureFlagValueChangedListener {

    fun onChange(featureFlag: FeatureFlag, value: Boolean)
}