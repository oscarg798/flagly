package com.oscarg798.flagly.developeroptions.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import com.flagly.databinding.ItemFeatureFlagBinding
import com.oscarg798.flagly.developeroptions.FeatureFlagValue

class FeatureFlagHandlerViewHolder(private val binding: ItemFeatureFlagBinding) :
    RecyclerView.ViewHolder(binding.root), LayoutContainer {

    override val containerView: View? = binding.root

    fun bind(
        featureFlagValue: FeatureFlagValue,
        featureFlagValueChangedListenerListener: FeatureFlagValueChangedListener
    ) {
        with(binding) {
            switchFeatureFlag.setOnCheckedChangeListener(null)
            cbOverride.setOnCheckedChangeListener(null)
            cbOverride.isChecked = featureFlagValue.isOverride
            switchFeatureFlag.isEnabled = featureFlagValue.isOverride
            switchFeatureFlag.isChecked = featureFlagValue.currentValue
            tvFeatureFlagName.text = featureFlagValue.featureFlag.name
            tvFeatureRemoteValue.text = featureFlagValue.remoteValue.toString()

            setupChangeListeners(featureFlagValueChangedListenerListener, featureFlagValue)
        }
    }

    private fun ItemFeatureFlagBinding.setupChangeListeners(
        featureFlagValueChangedListenerListener: FeatureFlagValueChangedListener,
        featureFlagValue: FeatureFlagValue
    ) {
        switchFeatureFlag.setOnCheckedChangeListener { _, isChecked ->
            featureFlagValueChangedListenerListener.onFeatureFlagValueChanged(
                featureFlagValue.featureFlag,
                isChecked
            )
        }

        cbOverride.setOnCheckedChangeListener { _, isChecked ->
            featureFlagValueChangedListenerListener.onOverrideValueChange(
                featureFlagValue.featureFlag,
                isChecked
            )
        }
    }
}