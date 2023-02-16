package com.novuspax.androidvisionboard

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.RelativeLayout
import com.novuspax.androidvisionboard.databinding.ActivityMainBinding
import com.novuspax.androidvisionboard.utils.Constants.COUNTER_STATE_KEY
import com.novuspax.androidvisionboard.utils.Constants.TEXT_SCALE_KEY

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var textScale = 0.9f
    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            counter = it.getInt(COUNTER_STATE_KEY)
            textScale = it.getFloat(TEXT_SCALE_KEY)
        }
        setCustomContentView()
        binding.tv.apply {
            text = "$counter"
            scaleX = textScale
            scaleY = textScale
        }

        binding.btnClick.setOnClickListener {
            textScale += 0.5f
            counter += 1
            binding.tv.text = "$counter"
            binding.tv.animate().scaleX(textScale)
            binding.tv.animate().scaleY(textScale)

        }
    }

    // when configuration changes happen we store data to bundle
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_STATE_KEY, counter)
        outState.putFloat(TEXT_SCALE_KEY, textScale)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // super needs to call first for restore
        super.onRestoreInstanceState(savedInstanceState)
        binding.tv.text = "${savedInstanceState.getInt(COUNTER_STATE_KEY)}"
        textScale = savedInstanceState.getFloat(TEXT_SCALE_KEY)
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