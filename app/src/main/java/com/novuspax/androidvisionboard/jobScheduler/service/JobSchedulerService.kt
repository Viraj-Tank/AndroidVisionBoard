package com.novuspax.androidvisionboard.jobScheduler.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner

// add permission in JobSchedulerService tag inside manifest
class JobSchedulerService : JobService() {

    private val TAG = "JobSchedulerService"
    private var isJobCancelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStartJob: ${params?.jobId}")
//        jobFinished(params, false)
//        return false
//        doBackgroundWork(params)
        downloadJson(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStopJob: ${params?.jobId}")
        isJobCancelled = true
        return true
//        return false
    }

    private fun downloadJson(params: JobParameters?) {
        Thread {
            val intent: Intent = Intent("jobSchedulerJsonAPICall")
            try {
                val url = URL("https://jsonplaceholder.typicode.com/todos/1")
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode: Int = connection.responseCode
                if (responseCode == 200) {
                    val scanner = Scanner(url.openStream())
                    val builder = java.lang.StringBuilder()
                    while (scanner.hasNext()) {
                        if (isJobCancelled) return@Thread
                        builder.append(scanner.nextLine())
                    }
                    Log.e(TAG, "downloadedJson: API Response$builder")

                    val obj: JSONObject = JSONObject(builder.toString())
                    Log.e(TAG, "downloadJson: Name only ${obj.getString("title")}")
                } else {

                }

            } catch (e: java.lang.Exception) {
                Log.e(TAG, "downloadJson: Catch block ${e.message}")
            }

        }.start()
    }

    private fun doBackgroundWork(params: JobParameters?) {
        Thread {
            repeat(10) {
                if (isJobCancelled) {
                    return@repeat
                }

                val jobIntent = Intent("jobIntentBroadcast")
                jobIntent.putExtra("num", it)
                sendBroadcast(jobIntent)

                Log.e(TAG, "doBackgroundWork: $it")

                Thread.sleep(1000)
            }
            Log.e(TAG, "doBackgroundWork: Job Done")
            jobFinished(params, false)
        }.start()

    }
}