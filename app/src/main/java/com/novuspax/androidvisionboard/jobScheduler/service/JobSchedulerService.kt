package com.novuspax.androidvisionboard.jobScheduler.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

// add permission in JobSchedulerService tag inside manifest
class JobSchedulerService : JobService() {

    private val TAG = "JobSchedulerService"
    private var isJobCancelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStartJob: ${params?.jobId}")
//        jobFinished(params, false)
//        return false
        doBackgroundWork(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStopJob: ${params?.jobId}")
        isJobCancelled = true
        return true
//        return false
    }

    private fun doBackgroundWork(params: JobParameters?) {
        Thread {
            repeat(10) {
                if (isJobCancelled) {
                    return@repeat
                }

                val jobIntent = Intent("jobIntentBroadcast")
                jobIntent.putExtra("num",it)
                sendBroadcast(jobIntent)

                Log.e(TAG, "doBackgroundWork: $it")

                Thread.sleep(1000)
            }
            Log.e(TAG, "doBackgroundWork: Job Done")
            jobFinished(params, false)
        }.start()

    }
}