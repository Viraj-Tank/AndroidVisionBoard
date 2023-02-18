package com.novuspax.androidvisionboard.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.e("TAG", "doWork: MyWorker")
        return Result.success()
    }

}