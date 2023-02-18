package com.novuspax.androidvisionboard.workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityContentProviderMainBinding
import com.novuspax.androidvisionboard.databinding.ActivityWorkManagerBinding

class WorkManagerActivity : AppCompatActivity() {

    private val binding: ActivityWorkManagerBinding by lazy {
        ActivityWorkManagerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnWorkManager.setOnClickListener {

            val cons = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            // set constraint in below work request
            val workRequest = OneTimeWorkRequest
                .Builder(MyWorker::class.java)
                .setConstraints(constraints = cons)
                .build()

            WorkManager.getInstance(this)
                .enqueue(workRequest) //--> it is same as JobInfo for jobschedular
        }
    }
}