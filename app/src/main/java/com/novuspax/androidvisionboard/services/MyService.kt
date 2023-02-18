package com.novuspax.androidvisionboard.services

import android.app.Activity
import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.novuspax.androidvisionboard.services.MyService.Companion.NOTIFICATION
import com.novuspax.androidvisionboard.services.MyService.Companion.result

class MyService : Service() {
    companion object {
        val NOTIFICATION: String = "com.novuspax.androidvisionboard.services.MyService"
        val TAG: String = "Running Service"
        var result = Activity.RESULT_CANCELED
    }


    override fun onBind(intent: Intent): IBinder? = null

    //    HERE WE DO THE REAL WORK
//    intent -> we can pass information here when we call the service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val number = intent?.getIntExtra("num", 1)
        Toast.makeText(this, "$number", Toast.LENGTH_SHORT).show()
        return START_NOT_STICKY
//        START_NOT_STICKY --> Service will not start if it get stopped by OS or pendingIntent invokes the service
    }
}


class MyIntentService : IntentService("myIntentService") {

    override fun onBind(intent: Intent): IBinder? = null

    // CALLED ASYNCHRONOUSLY BY ANDROID
    override fun onHandleIntent(intent: Intent?) {
        val number = intent?.getIntExtra("num", 1)
        result = Activity.RESULT_OK
        publishSum(number, result)
    }

    private fun publishSum(number: Int?, result: Int) {
//        enter this same action at the receiver side in registerReceiver method in activity
        val intent = Intent(NOTIFICATION)
        intent.putExtra("sum", number)
        intent.putExtra("result", result)
        sendBroadcast(intent)
    }
}