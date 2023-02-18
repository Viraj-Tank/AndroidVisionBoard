package com.novuspax.androidvisionboard.workManager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.e("TAG", "doWork: MyWorker")

//         receive data from activity
        val activityData = inputData.getString("data")

//        it works as live data we pass this data inside Result.success method
//        pass data to activity
        val data = Data.Builder()
            .putString("data","$activityData, Hello from Worker")
            .build()

        return Result.success(data)
    }

}