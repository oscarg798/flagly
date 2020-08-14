package com.oscarg798.flagy.featurepoint

import com.oscarg798.flagly.featurepoint.FeatureFactory
import com.oscarg798.flagly.featurepoint.FeaturePoint
import com.oscarg798.flagly.featurepoint.SuspendFeatureFactory
import com.oscarg798.flagly.featurepoint.SuspendFeaturePoint

abstract class AbstractSuspendFeaturePoint<Feature, Params>(override val factories: List<SuspendFeatureFactory<Feature, Params>>) :
    SuspendFeaturePoint<Feature, Params> {

    override suspend fun createFeatures(params: Params): List<Feature> = factories.filter {
        it.isApplicable(params)
    }.map {
        it.create()
    }
}