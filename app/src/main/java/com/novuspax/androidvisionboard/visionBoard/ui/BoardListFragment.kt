package com.novuspax.androidvisionboard.visionBoard.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.FragmentBoardListBinding
import com.novuspax.androidvisionboard.databinding.FragmentStaticBinding
import com.novuspax.androidvisionboard.visionBoard.adapter.BoardRecyclerViewAdapter
import com.novuspax.androidvisionboard.visionBoard.model.Board

class BoardListFragment : Fragment() {

    private lateinit var binding: FragmentBoardListBinding
    private var boardsList = mutableListOf<Board>()
    private var boardAdapter: BoardRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boardAdapter = BoardRecyclerViewAdapter(boardsList) { view, position ->
            val board = boardsList[position]
            startActivity(Intent(activity, VisionBoardDetailActivity::class.java).putExtra("board",board))
        }
        binding.rvBoard.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = boardAdapter
        }

    }

    fun setBoards(boardList: List<Board>) {
        for (board in boardList) {
            if (!boardsList.contains(board)) {
                boardsList.add(board)
                boardAdapter?.notifyItemInserted(boardsList.indexOf(board))
            }
        }
    }

}