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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public final class AnimationSampleActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.animation_sample_activity);

    final TextView tvCheckResult = (TextView) findViewById(R.id.tv_check_result);
    tvCheckResult.setAlpha(0);

    findViewById(R.id.bt_start_anim).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        tvCheckResult.animate()
            .setDuration(5000)
            .alpha(1)
            .withEndAction(new Runnable() {
              @Override public void run() {
                tvCheckResult.setText("Finish");
              }
            });
      }
    });
  }
}
