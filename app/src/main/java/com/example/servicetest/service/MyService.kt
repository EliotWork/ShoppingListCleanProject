package com.example.servicetest.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.security.Provider

class MyService: Service() {

    private val scope = CoroutineScope(Dispatchers.Main)


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        val start = intent?.getIntExtra(EXTRA_START,0) ?: 0
        scope.launch {
            for(i in start..start + 100){
                delay(1000)
                log("Timer $i")
            }
        }

        return START_REDELIVER_INTENT
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    private fun log(message:String){
        Log.d("Service_tag","MyService $message")
    }
    companion object{

        private const val EXTRA_START = "start"
        fun newIntent(context: Context,start: Int):Intent{
            return Intent(context,MyService::class.java).apply {
                putExtra(EXTRA_START, start)
            }
        }
    }
}