package com.example.coroutines_example.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.coroutines_example.common.KeyNotification.Companion.CHANNEL_ID
import com.example.coroutines_example.common.KeyNotification.Companion.CHANNEL_NAME

class CheckSDK:Application() {
    override fun onCreate() {
        super.onCreate()
        createNotification()
    }
    private fun createNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}