package com.example.kotlindemo

import android.animation.ArgbEvaluator
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.view.ViewGroup
import com.example.kotlindemo.animator.HSVEvaluator
import com.example.kotlindemo.animator.provinceName
import com.example.kotlindemo.customLayout.ColoredTextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_provinces.*
import kotlinx.coroutines.delay

class DemoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setContentView(R.layout.layout_motion_start)
//        setContentView(R.layout.layout_sample_contraint)
//        setContentView(R.layout.layout_motion_image)
//        setContentView(R.layout.layout_motion_hen)
//        val animator = ObjectAnimator.ofFloat(circleView, "radius", 150.dp)
//        animator.startDelay = 1000
//        animator.duration = 2000
//        animator.start()

        //circle animation
        /*val bottomFlipanimator = ObjectAnimator.ofFloat(cameraView, "bottomFlip", 60f)
        bottomFlipanimator.startDelay = 1000
        bottomFlipanimator.duration = 1500
        val flipRotationanimator = ObjectAnimator.ofFloat(cameraView, "flipRotation", 270f)
        flipRotationanimator.startDelay=200
        flipRotationanimator.duration = 1500
        val topFlipanimator = ObjectAnimator.ofFloat(cameraView, "topFlip", -60f)
        topFlipanimator.startDelay=200
        topFlipanimator.duration = 1500
        var set=AnimatorSet()
        set.playSequentially(bottomFlipanimator,flipRotationanimator,topFlipanimator)
        set.start()*/
        //keyframe

        //pointView animatior
//        var animator = ObjectAnimator.ofObject(pointView, "point", PointFEvaluator(), PointF(200.dp, 200.dp))
//        animator.startDelay = 1000
//        animator.duration = 2000
//        animator.start()

        //provinceView
//        var animator = ObjectAnimator.ofObject(provinceView, "name",
//                ProvinceTypedValue(), "北京", "澳门特别行政区")
//        animator.startDelay = 1000
//        animator.duration = 4000
//        animator.start()
//        var drawable=ColorDrawable()
//        drawable.toBitmap(10,10,Bitmap.Config.ARGB_8888)
        val objectAnim = ObjectAnimator.ofInt(colorView, "color", Color.RED, Color.GREEN)
        objectAnim.startDelay = 2000
        objectAnim.setEvaluator(HSVEvaluator())
//        objectAnim.setEvaluator(ArgbEvaluator())
        objectAnim.duration = 5000
        objectAnim.start()
    }
}
