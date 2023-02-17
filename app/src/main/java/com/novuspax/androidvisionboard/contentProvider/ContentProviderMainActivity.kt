package com.novuspax.androidvisionboard.contentProvider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.databinding.ActivityContentProviderMainBinding

class ContentProviderMainActivity : AppCompatActivity() {

    private val binding: ActivityContentProviderMainBinding by lazy {
        ActivityContentProviderMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}