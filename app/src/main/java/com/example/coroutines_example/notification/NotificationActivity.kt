package com.example.coroutines_example.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.coroutines_example.R
import com.example.coroutines_example.common.KeyNotification.Companion.CHANNEL_ID
import com.example.coroutines_example.common.KeyNotification.Companion.CHANNEL_NAME

class NotificationActivity : AppCompatActivity() {
    private lateinit var btnSendNotification: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnSendNotification = findViewById(R.id.btnSendNotification)

        btnSendNotification.setOnClickListener {
            Toast.makeText(this, "Click click ", Toast.LENGTH_SHORT).show()
            notificationChannel()
         }
    }

    private fun notificationChannel() {

        val mediaSessionCompat = MediaSessionCompat(this,"tag")

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
        Log.d("bitmap", "notificationChannel: $bitmap")
 
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_people)
            .setContentTitle(getString(R.string.txt_this_my_title))
            .setContentText(getString(R.string.txt_this_my_content))
            .setLargeIcon(bitmap)
                //set action
            .addAction(R.drawable.ic_skip_previous, "", null)
            .addAction(R.drawable.ic_pause, "", null)
            .addAction(R.drawable.ic_skip_next, "", null)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0,1,2)
                .setMediaSession(mediaSessionCompat.sessionToken))
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, notification)

    }


    private fun notificationChannel2() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_people)
            .setContentTitle(getString(R.string.txt_this_my_title))
            .setContentText(getString(R.string.txt_this_my_content))
            .build()

        notificationManager.notify(0, notification)
    }
}