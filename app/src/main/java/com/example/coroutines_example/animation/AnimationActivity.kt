package com.example.coroutines_example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.coroutines_example.R

class AnimationActivity : AppCompatActivity() {

    private lateinit var mViewAnimation : ImageView
    private lateinit var mImgAnimation : ImageView
    private lateinit var mTxtView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        mViewAnimation = findViewById(R.id.mViewAnimation)
        mImgAnimation = findViewById(R.id.mImgAnimation)
        mTxtView = findViewById(R.id.mTxtView)

        mImgAnimation.setOnClickListener {
         }
    }
}