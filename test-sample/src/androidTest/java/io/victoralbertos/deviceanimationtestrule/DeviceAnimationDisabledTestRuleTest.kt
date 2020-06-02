/*
 * Copyright 2016 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.victoralbertos.deviceanimationtestrule


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import io.victoralbertos.device_animation_test_rule.DeviceAnimationTestRule
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

class DeviceAnimationDisabledTestRuleTest {
    companion object {
        @ClassRule
        @JvmField
        val deviceAnimationTestRule = DeviceAnimationTestRule()
    }

    @get:Rule
    var activityRule = ActivityTestRule(AnimationSampleActivity::class.java)

    @Test
    fun verifyDisable() {
        onView(withId(R.id.bt_start_anim)).perform(click())
        onView(withId(R.id.tv_check_result)).check(matches(withText("Finish")))
    }
}