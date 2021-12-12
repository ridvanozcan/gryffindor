package com.hongbeomi.harrypotter.ui.ar

import android.app.Activity
import android.content.Context
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
import android.os.Build
import android.util.AttributeSet
import android.util.Base64
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment.OnTapArPlaneListener
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hongbeomi.harrypotter.databinding.ActivityInfoBinding
import kotlinx.android.synthetic.main.activity_ar.*
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class ArActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        FirebaseApp.initializeApp(this)
        val storage = FirebaseStorage.getInstance()
        val modelRef = storage.reference.child("out.glb")
        val modelRef1 = storage.reference.child("outt.glb")
        val modelRef2 = storage.reference.child("outtt.glb")
        val arFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as ArFragment?
        findViewById<View>(R.id.downloadBtn)
            .setOnClickListener { v: View? ->
                try {
                    Toast.makeText(this, "Model Hazırlanıyor Bekleyin...", Toast.LENGTH_LONG).show()
                    val file = File.createTempFile("out", "glb")
                    modelRef.getFile(file).addOnSuccessListener { buildModel(file) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        findViewById<View>(R.id.downloadBtnHogwarts)
            .setOnClickListener { v: View? ->
                try {
                    Toast.makeText(this, "Model Hazırlanıyor Bekleyin...", Toast.LENGTH_LONG).show()
                    val file = File.createTempFile("outt", "glb")
                    modelRef1.getFile(file).addOnSuccessListener { buildModel(file) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        findViewById<View>(R.id.downloadBtnHarry)
            .setOnClickListener { v: View? ->
                try {
                    Toast.makeText(this, "Model Hazırlanıyor Bekleyin...", Toast.LENGTH_LONG).show()
                    val file = File.createTempFile("outtt", "glb")
                    modelRef2.getFile(file).addOnSuccessListener { buildModel(file) }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            val anchorNode =
                AnchorNode(hitResult.createAnchor())
            anchorNode.renderable = renderable
            arFragment.arSceneView.scene.addChild(anchorNode)
        }
    }

    private var renderable: ModelRenderable? = null
    @RequiresApi(Build.VERSION_CODES.N)
    private fun buildModel(file: File) {
        val renderableSource = RenderableSource
            .builder()
            .setSource(this, Uri.parse(file.path), RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .build()
        ModelRenderable
            .builder()
            .setSource(this, renderableSource)
            .setRegistryId(file.path)
            .build()
            .thenAccept { modelRenderable: ModelRenderable? ->
                Toast.makeText(this, "Model Hazır. Ekrana Tıklayın", Toast.LENGTH_SHORT).show()
                renderable = modelRenderable
            }
    }
}