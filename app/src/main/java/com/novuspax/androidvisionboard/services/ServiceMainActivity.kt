package com.novuspax.androidvisionboard.services

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log.e
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityContentProviderMainBinding
import com.novuspax.androidvisionboard.databinding.ActivityServiceMainBinding

class ServiceMainActivity : AppCompatActivity() {

    private val binding: ActivityServiceMainBinding by lazy {
        ActivityServiceMainBinding.inflate(layoutInflater)
    }
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val bundle = intent?.extras
            bundle?.let {
                // check MyIntentService for this keys
                val sum = it.getInt("num")
                val resultCode = it.getInt("result")
                if (resultCode == RESULT_OK) {
                    binding.appCompatTextView.text = "Total sum $sum"
                } else {
                    binding.appCompatTextView.text = "Failed to receive in broadcast"
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
//        below IntentFilter(action) is defined inside MyService when we send the broadcast
//        registerReceiver(receiver, IntentFilter(MyService.NOTIFICATION))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerReceiver(receiver, IntentFilter(MyService.NOTIFICATION))

        binding.btnServiceStart.setOnClickListener {
            startService(
                Intent(this@ServiceMainActivity, MyIntentService()::class.java)
                    .putExtra("num", 25)
            )
        }

    }
}