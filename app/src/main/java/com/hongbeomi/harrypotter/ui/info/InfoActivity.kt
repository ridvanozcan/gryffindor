package com.hongbeomi.harrypotter.ui.info

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
import com.hongbeomi.harrypotter.databinding.ActivityInfoBinding
import java.nio.charset.Charset


class InfoActivity : BaseActivity() {

    private val binding: ActivityInfoBinding by binding(R.layout.activity_info)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@InfoActivity


        }
    }


    companion object {
        private const val KEY_INFO = "info"
        fun startInfoActivity(
            activity: Activity
        ) {
            val intent = Intent(activity, InfoActivity::class.java)
            activity.startActivity(intent)
        }

    }

}