package com.example.coroutines_example.slide_image_custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager.widget.ViewPager
import com.example.coroutines_example.R
import com.example.coroutines_example.slide_image_with_library.Photo
import com.example.coroutines_example.slide_image_with_library.adapter.PhotoAdapter
import me.relex.circleindicator.CircleIndicator
import java.util.*

/*
* Custom Slide Images with Viewpager + Indicator + Glide
* step 1: import library :
* https://github.com/ongakuer/CircleIndicator and https://github.com/bumptech/glide
*
**/
class CustomSlideActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var circleIndicator: CircleIndicator
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var mListPhoto: List<Photo>
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_slide)

        viewPager = findViewById(R.id.view_pager_slide)
        circleIndicator = findViewById(R.id.circle_indicator)
        mListPhoto = getListPhoto()

        photoAdapter = PhotoAdapter(mListPhoto, this)
        viewPager.adapter = photoAdapter

        //handling circle indicator
        circleIndicator.setViewPager(viewPager)
        photoAdapter.registerDataSetObserver(circleIndicator.dataSetObserver)

        autoSlide()
    }

    private fun getListPhoto(): List<Photo> {
        val list = mutableListOf<Photo>()
        list.add(Photo(R.drawable.x))
        list.add(Photo(R.drawable.x))
        list.add(Photo(R.drawable.x))
        list.add(Photo(R.drawable.x))
        return list
    }

    private fun autoSlide() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run() {
                        var currentItem = viewPager.currentItem
                        val totalItem = mListPhoto.size - 1
                        if (currentItem < totalItem) {
                            currentItem++
                            viewPager.currentItem = currentItem
                        } else {
                            viewPager.currentItem = 0
                        }
                    }

                })
            }

        }, 500, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}