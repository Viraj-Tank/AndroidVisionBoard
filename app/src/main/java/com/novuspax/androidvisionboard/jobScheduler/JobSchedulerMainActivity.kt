package com.novuspax.androidvisionboard.jobScheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityContentProviderMainBinding
import com.novuspax.androidvisionboard.databinding.ActivityJobSchedulerMainBinding
import com.novuspax.androidvisionboard.jobScheduler.service.JobSchedulerService

class JobSchedulerMainActivity : AppCompatActivity() {

    private val binding: ActivityJobSchedulerMainBinding by lazy {
        ActivityJobSchedulerMainBinding.inflate(layoutInflater)
    }
    val jobReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val bundle = intent?.extras
            bundle?.let {
                val number = it.getInt("num")
                binding.tvJobText.text = "$number"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnJob.setOnClickListener {
            val jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo: JobInfo = JobInfo.Builder(101, ComponentName(this, JobSchedulerService::class.java))
                .setMinimumLatency(0) // after how long we want to run this job
//                .setPersisted(true)
//                .setRequiresCharging(true)
//                .setPeriodic(15 * 60 * 1000) // minimum 15 minutes
                .build()

            jobScheduler.schedule(jobInfo)
        }
        binding.btnJobCancel.setOnClickListener {
            val jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(101)
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(jobReceiver, IntentFilter("jobIntentBroadcast"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(jobReceiver)
    }
}