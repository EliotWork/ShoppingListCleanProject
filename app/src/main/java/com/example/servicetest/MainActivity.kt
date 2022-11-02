package com.example.servicetest


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.servicetest.databinding.ActivityMainBinding
import com.example.servicetest.service.*
import com.example.servicetest.service.MyJobService.Companion.JOB_ID


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        service()
    }

    private fun service() = with(binding) {
        simpleService.setOnClickListener {
            stopService(MyForegroundService.newIntent(this@MainActivity))
            startService(Intent(MyService.newIntent(this@MainActivity, 25)))
        }
        foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(
                this@MainActivity,
                MyForegroundService.newIntent(this@MainActivity)
            )
        }
        intentService.setOnClickListener {
            ContextCompat.startForegroundService(
                this@MainActivity,
                MyIntentService.newIntent(this@MainActivity)
            )
        }
        jobScheduler.setOnClickListener {
            val componentName = ComponentName(this@MainActivity, MyJobService::class.java)

            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()

            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = MyJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            } else {
                startService(MyCombinationService.newIntent(this@MainActivity, page++))
            }
        }
        jobIntentService.setOnClickListener {
            MyJobIntentService.enqueue(this@MainActivity, page++)
        }
        workManager.setOnClickListener {
            val workManager = WorkManager.getInstance(applicationContext)
            workManager.enqueueUniqueWork(
                MyWorkManager.WORK_NAME,
                ExistingWorkPolicy.APPEND,
                MyWorkManager.makeRequest(page++)
            )
        }
    }
}
