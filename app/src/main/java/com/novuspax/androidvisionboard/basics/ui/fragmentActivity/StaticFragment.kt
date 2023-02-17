package com.novuspax.androidvisionboard.ui.fragmentActivity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novuspax.androidvisionboard.databinding.FragmentStaticBinding

class StaticFragment : Fragment() {

    private lateinit var binding: FragmentStaticBinding
    private var listener: OnSelectedItemListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvStaticFragment.setOnClickListener {
            listener?.clicked()
        }
    }

    fun setEditTextDataToTextView() {
        binding.apply {
            tvStaticFragment.text = edtStaticFragment.text.toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as OnSelectedItemListener
        } catch (e:Exception) {
            throw Exception(activity.toString() + " must implement onSomeEventListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSelectedItemListener {
        fun clicked()
    }

}