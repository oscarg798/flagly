package com.oscarg798.flagly.developeroptions

import kotlinx.coroutines.*
import com.oscarg798.flagly.featureflag.DynamicFeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlag
import com.oscarg798.flagly.featureflag.FeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagProvider
import com.oscarg798.flagly.utils.CoroutineContextProvider
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FeatureFlagHandlerPresenter @Inject constructor(
    private val featureFlagProvider: FeatureFlagProvider,
    private val remoteFeatureFlagHandler: FeatureFlagHandler,
    private val localFeatureflagHandler: DynamicFeatureFlagHandler,
    private val coroutineContextProvider: CoroutineContextProvider
) : FeatureFlagActivityContract.Presenter, CoroutineScope {

    private val job: Job = SupervisorJob()
    override var view: FeatureFlagActivityContract.View? = null

    override val coroutineContext: CoroutineContext
        get() = coroutineContextProvider.mainDispatcher + job

    override fun bind(view: FeatureFlagActivityContract.View) {
        this.view = view
    }

    override fun onViewReady() {
        view?.setup()
        setupFeatureFlagValues()
    }

    private fun setupFeatureFlagValues() {
        launch {
            val values = withContext(coroutineContextProvider.backgroundDispatcher) {
                featureFlagProvider.provideAppSupportedFeatureflags().map { featureFlag ->
                    getFeatureFlagValue(featureFlag)
                }
            }

            view?.showReatureFlagValues(values)
        }
    }

    override fun unBind() {
        view = null
    }

    override fun onFeatureFlagValueChanged(featureFlag: FeatureFlag, value: Boolean) {
        localFeatureflagHandler.setValue(featureFlag, value)
    }

    override fun onOverrideValueChange(featureFlag: FeatureFlag, override: Boolean) {
        if (override) {
            localFeatureflagHandler.setValue(featureFlag, false)
        } else {
            localFeatureflagHandler.removeOverridenValue(featureFlag)
        }
        setupFeatureFlagValues()
    }

    private fun getFeatureFlagValue(featureFlag: FeatureFlag): FeatureFlagValue {
        val isOverride = localFeatureflagHandler.isValueOverriden(featureFlag)
        return FeatureFlagValue(
            featureFlag,
            isOverride,
            getLocalValue(featureFlag, getLocalValue(featureFlag, isOverride)),
            remoteFeatureFlagHandler.isFeatureEnabled(featureFlag)
        )
    }

    private fun getLocalValue(featureFlag: FeatureFlag, isOverride: Boolean): Boolean =
        if (isOverride) {
            localFeatureflagHandler.isFeatureEnabled(featureFlag)
        } else {
            false
        }

}