package com.oscarg798.flagly.featurepoint

interface SuspendFeaturePoint<out Feature,in Params> {

    val factories: Collection<SuspendFeatureFactory<Feature, Params>>

    suspend fun createFeatures(params: Params): List<Feature>
}