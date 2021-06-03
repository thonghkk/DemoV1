package com.example.coroutines_example.coutinescontext

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.coroutines_example.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DemoCoroutineScope : CoroutineScope by CoroutineScope(Dispatchers.Default),
    AppCompatActivity() {
    private lateinit var mEdt1: EditText
    private lateinit var mEdt2: EditText
    private lateinit var mBtnSum: Button
    private lateinit var mTxtSum: TextView
    private lateinit var mImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_corountine_scope)
        val url = "https://data.chiasenhac.com/data/cover/86/85720.jpg"

        mEdt1 = findViewById(R.id.mEdt1)
        mEdt2 = findViewById(R.id.mEdt2)
        mTxtSum = findViewById(R.id.mTxtSum)
        mBtnSum = findViewById(R.id.mBtnSum)
        mImg = findViewById(R.id.mImg)

        // getBitmapFromURL()
        //getBitmap()
        getBM()
        getBitmapSecond()
        sum()
    }

    @SuppressLint("SetTextI18n")
    fun sum() {
        launch {

            mBtnSum.setOnClickListener {
                val num1 = mEdt1.text.toString()
                val num2 = mEdt2.text.toString()
                mTxtSum.text = (num1.toInt() + num2.toInt()).toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun getBitmapFromURL(): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL("https://data.chiasenhac.com/data/cover/86/85720.jpg")
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//            val connection:HttpURLConnection = url.openConnection() as HttpURLConnection
//            connection.doInput = true
//            connection.connect()
//            val input :InputStream = connection.inputStream
//            val bitmap : Bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {

        }
        Log.d("url", "getBitmapFromURL: $bitmap")
        return bitmap
    }

    fun getBitmap(): Bitmap? {
        var bitmap: Bitmap? = null
        val url = URL("https://data.chiasenhac.com/data/cover/86/85720.jpg")
        val httpConnect: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpConnect.connect()
        val resCode = httpConnect.responseCode
        if (resCode == HttpURLConnection.HTTP_OK) {
            val inputStream = httpConnect.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
        }
        Log.d("url", "getBitmapFromURL: $bitmap")
        return bitmap
    }

    fun getBM(): Bitmap? {
        var bitmap: Bitmap? = null
        Glide.with(this)
            .asBitmap()
            .load("https://data.chiasenhac.com/data/cover/86/85720.jpg")
            .fitCenter()
            .into(object : CustomTarget<Bitmap>(100, 100) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?)   {
                    bitmap = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
        Log.d("url", "getBitmapFromURL: $bitmap")

        return bitmap
    }

    private fun getBitmapSecond(){
        val bitmap:Bitmap = Glide.with(this).asBitmap()
            .load("https://data.chiasenhac.com/data/cover/86/85720.jpg").into(100,100).get()
        Log.d("url", "getBitmapSecond: $bitmap")
    }

}