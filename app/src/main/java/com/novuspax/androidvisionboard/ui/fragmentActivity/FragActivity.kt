package com.novuspax.androidvisionboard.ui.fragmentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityFragBinding
import com.novuspax.androidvisionboard.utils.Constants.HELLO_FRAGMENT
import com.novuspax.androidvisionboard.utils.Constants.SECOND_FRAGMENT

class FragActivity : AppCompatActivity(), StaticFragment.OnSelectedItemListener {

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

        binding.btnChange.setOnClickListener {
            supportFragmentManager.beginTransaction().let {
                it.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                it.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                it.replace(R.id.main_frame, SecondFragment(), SECOND_FRAGMENT)
                it.addToBackStack("Name")
                it.commit()
            }
        }
    }

    override fun clicked() {
        Toast.makeText(this@FragActivity, "Clicked", Toast.LENGTH_SHORT).show()
    }
}