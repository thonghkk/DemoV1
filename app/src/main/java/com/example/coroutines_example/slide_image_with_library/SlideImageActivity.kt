package com.example.coroutines_example.slide_image_with_library

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.coroutines_example.R
import jp.wasabeef.blurry.Blurry

/*
 * use library : "https://github.com/denzcoskun/ImageSlideshow"
 * */
class SlideImageActivity : AppCompatActivity() {
    private lateinit var mImageSlider:ImageSlider
    private lateinit var mBackground:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_image)

        mImageSlider = findViewById(R.id.mImageSlider)
        mBackground = findViewById(R.id.mBackground)
        val mListImage = mutableListOf<SlideModel>()
        mListImage.add(SlideModel("https://liveshowhay.vn/wp-content/uploads/2017/08/l%E1%BB%87-quy%C3%AAn.jpg",""))
        mListImage.add(SlideModel("https://i.ytimg.com/vi/YNGG12kECmg/hqdefault.jpg",""))
        mListImage.add(SlideModel("https://photos.eruce.com/thumbs/72b/d35/ab7/295/32b/8a0/853e45edb7af5da675cf1214cda0f5bd_500x500.jpg",""))
        mListImage.add(SlideModel("https://data.chiasenhac.com/data/cover/86/85720.jpg",""))

        mImageSlider.setImageList(mListImage,ScaleTypes.CENTER_CROP)

        Blurry.with(this).radius(25).sampling(100)
            .color(Color.argb(66, 255, 255, 0))
            .async()
            .animate(500)
            .capture(mBackground)
    }
}