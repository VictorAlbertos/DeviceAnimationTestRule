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

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class AnimationSampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_sample_activity)

        val tvCheckResult = findViewById<View>(R.id.tv_check_result) as TextView
        tvCheckResult.alpha = 0f
        findViewById<View>(R.id.bt_start_anim).setOnClickListener {
            tvCheckResult.animate()
                    .setDuration(5000)
                    .alpha(1f)
                    .withEndAction { tvCheckResult.text = "Finish" }
        }
    }
}