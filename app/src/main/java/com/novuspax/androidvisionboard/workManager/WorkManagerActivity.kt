package com.novuspax.androidvisionboard.workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.novuspax.androidvisionboard.databinding.ActivityWorkManagerBinding
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    private val binding: ActivityWorkManagerBinding by lazy {
        ActivityWorkManagerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnWorkManager.setOnClickListener {

            //types of constraint on which we want to invoke worker
            val cons = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            //pass data to our worker
            val data: Data = Data.Builder()
                .putString("data", "Data From Activity")
                .build()

            // set constraint in below work request
            val workRequest = OneTimeWorkRequest
                .Builder(MyWorker::class.java)
                .setConstraints(constraints = cons)
                .addTag("tag")
                .setInputData(data)
                .build()

            WorkManager.getInstance(this)
                .enqueue(workRequest) //--> workRequest is same as JobInfo for jobScheduler

            // receive data from worker here
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(workRequest.id)
                .observe(this) {
                    if (it.state == WorkInfo.State.SUCCEEDED) {
                        val result: String = it.outputData.getString("data").toString()
                        binding.tvWorkManager.text = result
                    }
                }
        }
        binding.btnWorkManagerPeriodic.setOnClickListener {
            //types of constraint on which we want to invoke worker
            val cons = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            // this request going to run every 10 hours
            val periodicRequest = PeriodicWorkRequest
                .Builder(MyWorker::class.java, 10, TimeUnit.HOURS)
                .setConstraints(constraints = cons)
                .addTag("tag")
                .build()

            WorkManager.getInstance(this).enqueue(periodicRequest)


            val workRequest = OneTimeWorkRequest
                .Builder(MyWorker::class.java)
                .setConstraints(constraints = cons)
                .addTag("tag")
                .build()


            //chaining worker
            WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork("Unique", ExistingPeriodicWorkPolicy.KEEP, periodicRequest)
//                .beginWith(workRequest)
//                .then(workRequest) // add other requests here
//                .then(workRequest)
//                .enqueue()

        }
    }

    fun cancelWork(workRequest: WorkRequest) {
        WorkManager.getInstance(this)
            .cancelWorkById(workRequest.id)

        // you need to pass this tag inside main workRequest
        WorkManager.getInstance(this)
            .cancelAllWorkByTag("tag")

    }
}