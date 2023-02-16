package com.novuspax.androidvisionboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat.animate
import com.novuspax.androidvisionboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var rotVal = 0.9f
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomContentView()

        binding.btnClick.setOnClickListener {
            rotVal += 1
            binding.tv.animate().scaleX(rotVal)
        }
    }

    private fun setCustomContentView() {
        val button = Button(this@MainActivity)
        button.setText("Direct Added Button From View!")

        val myLayout = RelativeLayout(this@MainActivity)

        val myLayoutParams = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        myLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        myLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL)
        myLayout.layoutParams = myLayoutParams

        myLayout.addView(button)

//        setContentView(myLayout,myLayoutParams)
          setContentView(binding.root)
    }
}