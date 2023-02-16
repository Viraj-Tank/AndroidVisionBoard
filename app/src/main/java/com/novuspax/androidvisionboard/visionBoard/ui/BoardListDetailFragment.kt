package com.novuspax.androidvisionboard.visionBoard.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.FragmentBoardListBinding
import com.novuspax.androidvisionboard.databinding.FragmentBoardListDetailBinding
import com.novuspax.androidvisionboard.visionBoard.model.Board
import kotlin.concurrent.fixedRateTimer


class BoardListDetailFragment : Fragment() {

    private lateinit var binding: FragmentBoardListDetailBinding
    private var board: Board? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                board = it.getSerializable("board", Board::class.java)
            } else {
                board = it.getSerializable("board") as Board
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvDes.text = board?.description
            tvName.text = board?.name
        }
    }

    companion object {
        fun newInstance(board: Board): BoardListDetailFragment {

            return BoardListDetailFragment().apply {
                val args = Bundle()
                args.putSerializable("board", board)
                arguments = args
            }

            /*
            return BoardListDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("name", board.name)
                    putString("des",board.description )
                }
            }
            */
        }
    }

}