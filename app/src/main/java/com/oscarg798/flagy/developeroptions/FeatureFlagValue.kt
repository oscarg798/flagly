package com.oscarg798.flagly.developeroptions

import com.oscarg798.flagly.featureflag.FeatureFlag

data class FeatureFlagValue(
    val featureFlag: FeatureFlag,
    val isOverride: Boolean,
    val currentValue: Boolean,
    val remoteValue: Boolean
)
