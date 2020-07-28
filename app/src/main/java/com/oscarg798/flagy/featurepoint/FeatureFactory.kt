package com.oscarg798.flagly.featurepoint

interface FeatureFactory<out Feature,in Params> {

    fun create(): Feature

    fun isApplicable(params: Params): Boolean
}