package com.hongbeomi.harrypotter.ui.video

/**
 * Copyright 2020 Hongbeom Ahn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.hongbeomi.harrypotter.R
import com.hongbeomi.harrypotter.base.BaseActivity
import com.hongbeomi.harrypotter.databinding.ActivityVideoBinding
import com.hongbeomi.harrypotter.ui.HouseType
import com.hongbeomi.harrypotter.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : BaseActivity() {

    private val binding: ActivityVideoBinding by binding(R.layout.activity_video)
    private val videoData: String by lazy {
        intent.getStringExtra(KEY_VIDEO)!!
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@VideoActivity

            videoView.setVideoURI(Uri.parse(videoData))
            videoView.start()

            videoView.setOnClickListener {
                videoView.start()
            }
        }
    }


    companion object {
        private const val KEY_VIDEO = "video"
        fun startVideoActivity(
            activity: Activity,
            data: String
        ) {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra(KEY_VIDEO, data)
            activity.startActivity(intent)
        }

    }

}