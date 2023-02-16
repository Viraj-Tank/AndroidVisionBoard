package com.novuspax.androidvisionboard.ui.fragmentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityFragBinding
import com.novuspax.androidvisionboard.databinding.ActivityMainBinding

class FragActivity : AppCompatActivity() {

    val HELLO_FRAGMENT = "HELLO_TAG"
    private val binding: ActivityFragBinding by lazy {
        ActivityFragBinding.inflate(layoutInflater)
    }
    var frag = StaticFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // for static fragment please check xml file with fragment tag

        if (savedInstanceState == null) {
            /*val fragmentManager = supportFragmentManager
              val transaction = fragmentManager.beginTransaction()
//              can save this fragment into global variable also so we can use values across whole file
              val fragment = StaticFragment()
              transaction.add(R.id.main_frame, fragment, HELLO_FRAGMENT)
              transaction.commit()*/

            /// OR

            supportFragmentManager.beginTransaction().add(R.id.main_frame, frag, HELLO_FRAGMENT).commit()
        } else {
            // can save this fragment into global variable also so we can use values across whole file
            supportFragmentManager.findFragmentByTag(HELLO_FRAGMENT)
        }

        binding.btnCallFragMethod.setOnClickListener {
            // here findFragmentByTag method returns fragment so we're type casting it
            supportFragmentManager.findFragmentByTag(HELLO_FRAGMENT)?.let {
                (it as StaticFragment).setEditTextDataToTextView()
            }
            ///OR
            /*frag = supportFragmentManager.findFragmentByTag(HELLO_FRAGMENT) as StaticFragment
            frag.setEditTextDataToTextView()*/
        }
    }
}