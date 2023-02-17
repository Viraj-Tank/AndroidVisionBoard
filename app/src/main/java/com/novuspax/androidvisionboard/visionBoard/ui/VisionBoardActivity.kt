package com.novuspax.androidvisionboard.visionBoard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityVisionBoardBinding
import com.novuspax.androidvisionboard.basics.utils.Constants.BOARD_FRAGMENT
import com.novuspax.androidvisionboard.visionBoard.model.Board

class VisionBoardActivity : AppCompatActivity() {

    private val binding: ActivityVisionBoardBinding by lazy {
        ActivityVisionBoardBinding.inflate(layoutInflater)
    }
    private var boardFragment = BoardListFragment()
    private var dummyBoards = mutableListOf<Board>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.visionFrame, boardFragment, BOARD_FRAGMENT).commit()
        } else {
            supportFragmentManager.findFragmentByTag(BOARD_FRAGMENT).let {
                boardFragment = it as BoardListFragment
            }
        }

        dummyBoards.add(
            Board(
                name = "Travel",
                description = "Travel with family",
                imageUrl = R.drawable.ic_launcher_foreground
            )
        )
        dummyBoards.add(
            Board(
                name = "Buy a house",
                description = "Travel with family",
                imageUrl = R.drawable.ic_launcher_foreground
            )
        )
        dummyBoards.add(
            Board(
                name = "Invest",
                description = "Travel with family",
                imageUrl = R.drawable.ic_launcher_foreground
            )
        )
        dummyBoards.add(
            Board(
                name = "Be tree",
                description = "Travel with family",
                imageUrl = R.drawable.ic_launcher_foreground
            )
        )

        boardFragment.setBoards(dummyBoards)

    }
}