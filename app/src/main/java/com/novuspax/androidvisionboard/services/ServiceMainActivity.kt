package com.novuspax.androidvisionboard.services

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log.d
import android.util.Log.e
import android.widget.Toast
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

    var binderService: MyBinderService? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // here we will have connection with service and we can call any methods from service
            val binder = service as MyBinderService.ServiceBinder
            binderService = binder.getService()
            e("TAG", "onServiceConnected: ", )
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            if (binderService != null) {
                binderService = null
            }
            e("TAG", "onServiceDisconnected: ")
        }
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

        binding.btnServiceMethod.setOnClickListener {
            val data = binderService?.methodInsideService()
            Toast.makeText(this@ServiceMainActivity, data, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        val serviceIntent = Intent(this, MyBinderService::class.java)
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        // BIND_AUTO_CREATE --> when i bind to activity we create the service automatically
        e("TAG", "onStart: Service Started")
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
        e("TAG", "onStop: Service unbound", )
    }

    override fun onResume() {
        super.onResume()
//        below IntentFilter(action) is defined inside MyService when we send the broadcast
//        registerReceiver(receiver, IntentFilter(MyService.NOTIFICATION))
    }
}