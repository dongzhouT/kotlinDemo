package com.example.kotlindemo.animator

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.example.kotlindemo.R
import com.example.kotlindemo.dp
import kotlinx.android.synthetic.main.activity_anim_demo.*
import kotlinx.android.synthetic.main.layotu_constraintdemo.*

class AnimDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_demo)
        //keyframe 关键帧
        val length = 200.dp
        var keyFrame1 = Keyframe.ofFloat(0f, 0f)
        var keyFrame2 = Keyframe.ofFloat(0.2f, 1.5f * length)
        var keyFrame3 = Keyframe.ofFloat(0.8f, 0.8f * length)
        var keyFrame4 = Keyframe.ofFloat(1f, length)
        var keyframeHolder = PropertyValuesHolder.ofKeyframe("translationX", keyFrame1
                , keyFrame2, keyFrame3, keyFrame4)
        var animator = ObjectAnimator.ofPropertyValuesHolder(imageAnim, keyframeHolder)
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
        var bitmap=Bitmap.createBitmap(20,20,Bitmap.Config.ARGB_8888)
        bitmap.toDrawable(resources)
        val drawable=ColorDrawable()
        drawable.toBitmap()
//        view.animate().translationX(100.dp).withLayer()

//        TypeEvaluator()
//        ObjectAnimator.ofObject(view, TypeEvaluator(),??)

    }
}