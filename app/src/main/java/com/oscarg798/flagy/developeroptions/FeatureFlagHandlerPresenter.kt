package com.oscarg798.flagly.developeroptions

import kotlinx.coroutines.*
import com.oscarg798.flagly.featureflag.DynamicFeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlag
import com.oscarg798.flagly.featureflag.FeatureFlagHandler
import com.oscarg798.flagly.featureflag.FeatureFlagProvider
import com.oscarg798.flagly.utils.CoroutineContextProvider
import com.oscarg798.flagy.exceptions.FeatureFlagNotPresentInHandlerException
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
        launch {
            val values = withContext(coroutineContextProvider.backgroundDispatcher) {
                featureFlagProvider.provideAppSupportedFeatureflags().map { featureFlag ->
                    FeatureFlagValue(
                        featureFlag,
                        getLocalValue(featureFlag),
                        remoteFeatureFlagHandler.isFeatureEnabled(featureFlag)
                    )
                }
            }

            view?.showReatureFlagValues(values)
        }
    }

    override fun unBind() {
        view = null
    }

    override fun onChange(featureFlag: FeatureFlag, value: Boolean) {
        localFeatureflagHandler.setValue(featureFlag, value)
    }

    private fun getLocalValue(featureFlag: FeatureFlag): Boolean {
        return try {
            localFeatureflagHandler.isFeatureEnabled(featureFlag)
        } catch (e: FeatureFlagNotPresentInHandlerException) {
            false
        }
    }
}