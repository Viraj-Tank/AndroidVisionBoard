package com.novuspax.androidvisionboard.ui.fragmentActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.FragmentStaticBinding

class StaticFragment : Fragment() {

    lateinit var binding: FragmentStaticBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaticBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setEditTextDataToTextView() {
        binding.apply {
            tvStaticFragment.text = edtStaticFragment.text.toString()
        }
    }

}