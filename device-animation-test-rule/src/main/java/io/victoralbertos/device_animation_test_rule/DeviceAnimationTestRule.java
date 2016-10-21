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

package io.victoralbertos.device_animation_test_rule;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import java.lang.reflect.Method;
import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Disable device animations prior to running any test, and enable them after every test
 * has been executed. Usage: add this rule as an static field annotated with {@link ClassRule} to
 * your suit. As follows: {@literal @}ClassRule static public DeviceAnimationTestRule rule = new
 * DeviceAnimationTestRule();
 */
public final class DeviceAnimationTestRule implements TestRule {
  private static final String ANIMATION_PERMISSION = "android.permission.SET_ANIMATION_SCALE";
  private static final float DISABLED = 0.0f;
  private static final float DEFAULT = 1.0f;

  @Override public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override public void evaluate() throws Throwable {
        disableAnimations();
        try {
          base.evaluate();
        } finally {
          enableAnimations();
        }
      }
    };
  }

  private void disableAnimations() throws Exception {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

    UiDevice
        .getInstance(InstrumentationRegistry.getInstrumentation())
        .executeShellCommand(
            "pm grant "
                + InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName()
                + " android.permission.SET_ANIMATION_SCALE"
        );

    Context context = instrumentation.getContext();

    int permStatus = context.checkCallingOrSelfPermission(ANIMATION_PERMISSION);
    if (permStatus == PackageManager.PERMISSION_GRANTED) {
      setSystemAnimationsScale(DISABLED);
    }
  }

  private void enableAnimations() throws Exception {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    Context context = instrumentation.getContext();

    int permStatus = context.checkCallingOrSelfPermission(ANIMATION_PERMISSION);
    if (permStatus == PackageManager.PERMISSION_GRANTED) {
      setSystemAnimationsScale(DEFAULT);
    }
  }

  private void setSystemAnimationsScale(float animationScale) throws Exception {
    Class<?> windowManagerStubClazz = Class.forName("android.view.IWindowManager$Stub");
    Method asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder.class);
    Class<?> serviceManagerClazz = Class.forName("android.os.ServiceManager");
    Method getService = serviceManagerClazz.getDeclaredMethod("getService", String.class);
    Class<?> windowManagerClazz = Class.forName("android.view.IWindowManager");
    Method setAnimationScales =
        windowManagerClazz.getDeclaredMethod("setAnimationScales", float[].class);
    Method getAnimationScales = windowManagerClazz.getDeclaredMethod("getAnimationScales");

    IBinder windowManagerBinder = (IBinder) getService.invoke(null, "window");
    Object windowManagerObj = asInterface.invoke(null, windowManagerBinder);
    float[] currentScales = (float[]) getAnimationScales.invoke(windowManagerObj);
    for (int i = 0; i < currentScales.length; i++) {
      currentScales[i] = animationScale;
    }
    setAnimationScales.invoke(windowManagerObj, new Object[] {currentScales});
  }
}
