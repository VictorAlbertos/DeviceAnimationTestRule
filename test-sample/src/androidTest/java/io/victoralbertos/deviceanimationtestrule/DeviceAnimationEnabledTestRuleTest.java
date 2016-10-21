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

package io.victoralbertos.deviceanimationtestrule;

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public final class DeviceAnimationEnabledTestRuleTest {
  @Rule public ActivityTestRule<AnimationSampleActivity> activityRule =
      new ActivityTestRule<>(AnimationSampleActivity.class);

  @Test(expected = Throwable.class) public void Verify_Enabled() {
    onView(withId(R.id.bt_start_anim)).perform(click());
    onView(withId(R.id.tv_check_result)).check(matches(withText("Finish")));
  }
}