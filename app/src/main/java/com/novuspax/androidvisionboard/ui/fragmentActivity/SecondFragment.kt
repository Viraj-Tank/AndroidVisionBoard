package com.novuspax.androidvisionboard.ui.fragmentActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.FragmentSecondBinding
import com.novuspax.androidvisionboard.databinding.FragmentStaticBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

}