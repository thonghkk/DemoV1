package com.example.coroutines_example.slide_image_with_library.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.coroutines_example.R
import com.example.coroutines_example.slide_image_with_library.Photo

class PhotoAdapter(private val photos: List<Photo>, val context: Context) : PagerAdapter() {
    override fun getCount() = photos.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    //add image to view group
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.item_photo, container, false)
        val imgPhoto = view.findViewById<ImageView>(R.id.mImgPhoto)
        val photo = photos[position]
        Glide.with(context).load(photo.resourceId).into(imgPhoto)
        container.addView(view)
        return view
    }

    //remove View
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}