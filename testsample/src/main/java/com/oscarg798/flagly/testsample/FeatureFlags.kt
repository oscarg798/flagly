package com.oscarg798.flagly.testsample

import com.oscarg798.flagly.featureflag.FeatureFlag

object FeatureFlagOne : FeatureFlag{
    override val name: String
        get() = "Feature Flag One"
}

object FeatureFlagTwo : FeatureFlag{
    override val name: String
        get() = "Feature Flag Two"
}


object FeatureFlagThree : FeatureFlag{
    override val name: String
        get() = "Feature Flag Three"
}

object FeatureFlagFour : FeatureFlag{
    override val name: String
        get() = "Feature Flag Four"
}