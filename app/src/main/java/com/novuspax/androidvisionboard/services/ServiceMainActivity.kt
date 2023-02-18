package com.novuspax.androidvisionboard.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityContentProviderMainBinding
import com.novuspax.androidvisionboard.databinding.ActivityServiceMainBinding

class ServiceMainActivity : AppCompatActivity() {

    private val binding: ActivityServiceMainBinding by lazy {
        ActivityServiceMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnServiceStart.setOnClickListener {
            startService(Intent(this@ServiceMainActivity,MyService::class.java).putExtra("num",25))
        }
    }
}