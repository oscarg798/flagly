package com.oscarg798.flagy.exceptions

import com.oscarg798.flagly.featureflag.FeatureFlag

class FeatureFlagNotPresentInHandlerException(featureFlag: FeatureFlag)
    : IllegalArgumentException("${featureFlag.name} is not present in feature flag handler")