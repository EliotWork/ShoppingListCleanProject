package com.example.servicetest.service

import android.app.*
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

class MyIntentService: IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(false)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for(i in 0 until 5){
            Thread.sleep(1000)
            log("Timer $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message:String){
        Log.d("Service_tag","MyIntentService $message")
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
        private const val NAME = "my_intent_service"
        fun newIntent(context: Context):Intent{
            return Intent(context,MyIntentService::class.java)
        }
    }
}