package com.example.servicetest.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.servicetest.MainActivity
import com.example.servicetest.R
import kotlinx.coroutines.*
import java.security.Provider

class MyForegroundService: Service() {

    private val scope = CoroutineScope(Dispatchers.Main)


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        scope.launch {
            for(i in 0 until 100){
                delay(1000)
                log("Timer $i")
            }
            stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    private fun log(message:String){
        Log.d("Service_tag","MyForegroundService $message")
    }

    private fun createNotification():Notification{
        return NotificationCompat.Builder(this, CHANEL_ID)
            .setContentTitle("ServiceTest")
            .setContentText("Working")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }


    private fun createNotificationChannel(){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChanel = NotificationChannel(
                CHANEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChanel)
        }
    }

    companion object{

        private const val CHANEL_ID = "chanel_id"
        private const val CHANNEL_NAME = "Working"
        private const val NOTIFICATION_ID = 1
        fun newIntent(context: Context):Intent{
            return Intent(context,MyForegroundService::class.java)
        }
    }
}