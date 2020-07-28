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
            binding.switchFeatureFlag.isChecked = featureFlagValue.currentValue
            tvFeatureFlagName.text = featureFlagValue.featureFlag.name
            tvFeatureRemoteValue.text = featureFlagValue.remoteValue.toString()
            switchFeatureFlag.setOnCheckedChangeListener { _, isChecked ->
                featureFlagValueChangedListenerListener.onChange(
                    featureFlagValue.featureFlag,
                    isChecked
                )
            }
        }
    }
}