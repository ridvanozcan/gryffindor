package com.hongbeomi.harrypotter.ui.image

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hongbeomi.harrypotter.R
import com.hongbeomi.harrypotter.base.BaseActivity
import com.hongbeomi.harrypotter.databinding.ActivityImageBinding
import java.util.logging.Level.parse
import android.graphics.BitmapFactory
import android.util.Base64
import java.nio.charset.Charset


class ImageActivity : BaseActivity() {

    private val binding: ActivityImageBinding by binding(R.layout.activity_image)
    private val imageData: String by lazy {
        intent.getStringExtra(KEY_IMAGE)!!
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@ImageActivity
            val imageBytes = Base64.decode(imageData, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            imageView.setImageBitmap(image)

        }
    }


    companion object {
        private const val KEY_IMAGE = "image"
        fun startImageActivity(
            activity: Activity,
            data: String
        ) {
            val intent = Intent(activity, ImageActivity::class.java)
            intent.putExtra(KEY_IMAGE, data)
            activity.startActivity(intent)
        }

    }

}