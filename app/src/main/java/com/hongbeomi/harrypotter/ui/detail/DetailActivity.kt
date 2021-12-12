package com.hongbeomi.harrypotter.ui.detail

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
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.hongbeomi.harrypotter.R
import com.hongbeomi.harrypotter.base.BaseActivity
import com.hongbeomi.harrypotter.databinding.ActivityDetailBinding
import com.hongbeomi.harrypotter.ui.HouseType
import com.hongbeomi.harrypotter.ui.video.VideoActivity.Companion.startVideoActivity
import com.hongbeomi.harrypotter.ui.image.ImageActivity.Companion.startImageActivity
import android.graphics.Bitmap
import android.content.ActivityNotFoundException
import android.util.Base64
import android.widget.Toast
import com.hongbeomi.harrypotter.ui.ar.ArActivity
import com.hongbeomi.harrypotter.ui.info.InfoActivity.Companion.startInfoActivity
import java.io.ByteArrayOutputStream


class DetailActivity : BaseActivity() {

    companion object {
        private const val KEY_HOUSE = "house"
        private var temp: Boolean = false
        fun startActivityWithTransition(
            activity: Activity,
            imageView: ImageView,
            type: HouseType
        ) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(KEY_HOUSE, type)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, imageView, imageView.transitionName
            )
            activity.startActivity(intent, options.toBundle())
        }
    }

    private val binding by binding<ActivityDetailBinding>(R.layout.activity_detail)
    private val house by lazy { intent.getSerializableExtra(KEY_HOUSE) as? HouseType }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            house = this@DetailActivity.house
            lifecycleOwner = this@DetailActivity

            videoCek.setOnClickListener {
              /*  val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0)
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5)
                startActivityForResult(intent,1)*/
                Toast.makeText(applicationContext, "Yakında...",Toast.LENGTH_SHORT).show()
            }

            fotografCek.setOnClickListener {
                Toast.makeText(applicationContext, "Yakında...",Toast.LENGTH_SHORT).show()
              /*  temp = true
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, 1)
                } catch (e: ActivityNotFoundException) {

                }*/
            }

            bizKimiz.setOnClickListener {
                startInfoActivity(this@DetailActivity)
            }

            arAlani.setOnClickListener {
                val intent = Intent(this@DetailActivity, ArActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) {
            if (!temp)
                startVideoActivity(this@DetailActivity, data?.data.toString())
            else {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                startImageActivity(this@DetailActivity, BitMapToString(imageBitmap))
            }
        }
    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}