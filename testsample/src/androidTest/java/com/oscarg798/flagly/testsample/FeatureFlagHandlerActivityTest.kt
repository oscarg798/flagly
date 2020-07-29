package com.oscarg798.flagly.testsample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.oscarg798.flagly.developeroptions.FeatureFlagHandlerActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class FeatureFlagHandlerActivityTest {

    @get:Rule
    val intentTestRule: IntentsTestRule<FeatureFlagHandlerActivity> =
        IntentsTestRule(FeatureFlagHandlerActivity::class.java, true, true)

    @Test
    fun given_and_app_with_four_feature_flags_the_one_and_three_ON_when_view_is_shown_it_should_match() {
        areFeatureFlagsDisplayed()
        areRemoteValuesDisplayedCorrectly()
    }

    @Test
    fun given_a_value_is_not_overriden_when_click_on_switch_then_nothign_should_happen() {
        clickOnViewWithId(R.id.switchFeatureFlag)
        isViewNotChecked(R.id.switchFeatureFlag)
    }

    @Test
    fun given_a_value_is_overriden_when_click_on_switch_that_is_off_then_value_should_change() {
        clickOnViewWithWithText(OVERRIDE_TEXT,THIRD_FEATURE_FLAG_INDEX)
        clickOnViewWithId(R.id.switchFeatureFlag,THIRD_FEATURE_FLAG_INDEX)
        isViewCheck(R.id.switchFeatureFlag,THIRD_FEATURE_FLAG_INDEX)
    }


    private fun areRemoteValuesDisplayedCorrectly() {
        isViewContainingTextDisplayed(ON_FEATURE_FLAG_TEXT, REPEATED_TEXT_FIRST_MATCH_INDEX_POSITION)
        isViewContainingTextDisplayed(OFF_FEATURE_FLAG_TEXT, REPEATED_TEXT_FIRST_MATCH_INDEX_POSITION)
        isViewContainingTextDisplayed(ON_FEATURE_FLAG_TEXT, REPEATED_TEXT_SECOND_MATCH_INDEX_POSITION)
        isViewContainingTextDisplayed(OFF_FEATURE_FLAG_TEXT, REPEATED_TEXT_SECOND_MATCH_INDEX_POSITION)
    }

    private fun areFeatureFlagsDisplayed() {
        isViewContainingTextDisplayed("one")
        isViewContainingTextDisplayed("two")
        isViewContainingTextDisplayed("three")
        isViewContainingTextDisplayed("four")
    }
}

private const val OVERRIDE_TEXT = "Override"
private const val THIRD_FEATURE_FLAG_INDEX = 2
private const val ON_FEATURE_FLAG_TEXT = "true"
private const val OFF_FEATURE_FLAG_TEXT = "false"
private const val REPEATED_TEXT_FIRST_MATCH_INDEX_POSITION = 0
private const val REPEATED_TEXT_SECOND_MATCH_INDEX_POSITION = 1