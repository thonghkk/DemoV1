package com.example.coroutines_example.animation

import android.graphics.Bitmap
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView


class AnimationUntil {
      val ANIMATION_DURATION = 1000
      val isAnimationStart = false

    fun translateAnimation(viewAnimation:ImageView, startView: View,endView:View,animationListener: Animation.AnimationListener){
        startView.isDrawingCacheEnabled = true
        val cache:Bitmap = startView.getDrawingCache()

        val bitmap = Bitmap.createBitmap(cache)
        startView.isDrawingCacheEnabled = false

        viewAnimation.setImageBitmap(bitmap)

        val startViewWidthCenter = startView.width / 2
        val startViewHeightCenter = startView.height / 2

        val endViewWidthCenter = startView.width * 0.75
        val endViewHeightCenter = startView.height * 2

        val startPos = arrayOf(2)
     }


}