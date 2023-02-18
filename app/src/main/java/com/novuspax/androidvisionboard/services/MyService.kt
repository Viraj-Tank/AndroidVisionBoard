package com.novuspax.androidvisionboard.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

//    HERE WE DO THE REAL WORK
//    intent -> we can pass information here when we call the service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val number = intent?.getIntExtra("num",1)
        Toast.makeText(this, "$number", Toast.LENGTH_SHORT).show()
        return Service.START_NOT_STICKY
//        START_NOT_STICKY --> Service will not start if it get stopped by OS or pendingIntent invokes the service
    }
}


// IT WILL RUN ASYNCHRONOUSLY
class MyIntentService(name: String?) : IntentService(name) {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onHandleIntent(intent: Intent?) {
        val number = intent?.getIntExtra("num",1)
        Toast.makeText(this, "$number", Toast.LENGTH_SHORT).show()
    }
}