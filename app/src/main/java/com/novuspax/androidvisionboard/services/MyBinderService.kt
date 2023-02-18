package com.novuspax.androidvisionboard.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

// bound services run on main thread
// bind services are great to wait between api responses
// 1.create ServiceBinder and return it into onBind method
// 2.setup serviceConnection for service in activity
// 3.now you can any method of service from activity
class MyBinderService : Service() {

    private val binder = ServiceBinder()

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("TAG", "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("TAG", "onBind: ", )
        return binder
    }

    fun methodInsideService(): String = "Method Inside Service"


    inner class ServiceBinder : Binder() {
        fun getService(): MyBinderService {
            return MyBinderService()
        }
    }

}
