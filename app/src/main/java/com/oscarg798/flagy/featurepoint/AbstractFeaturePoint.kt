package com.oscarg798.flagly.featurepoint

abstract class AbstractFeaturePoint<Feature, Params>(override val factories: List<FeatureFactory<Feature, Params>>) :
    FeaturePoint<Feature, Params> {

    override fun createFeatures(params: Params): List<Feature> = factories.filter {
        it.isApplicable(params)
    }.map {
        it.create()
    }
}