package com.novuspax.androidvisionboard.visionBoard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.novuspax.androidvisionboard.R
import com.novuspax.androidvisionboard.databinding.ActivityMainBinding
import com.novuspax.androidvisionboard.databinding.ActivityVisionBoardDetailBinding
import com.novuspax.androidvisionboard.utils.Constants.BOARD_FRAGMENT
import com.novuspax.androidvisionboard.visionBoard.model.Board

class VisionBoardDetailActivity : AppCompatActivity() {

    private val binding: ActivityVisionBoardDetailBinding by lazy {
        ActivityVisionBoardDetailBinding.inflate(layoutInflater)
    }
    var boardDetailFragment = BoardListDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (intent.hasExtra("board")) {
            boardDetailFragment = BoardListDetailFragment.newInstance(intent.getSerializableExtra("board") as Board)
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction().replace(R.id.visionBoardDetailFrame, boardDetailFragment, BOARD_FRAGMENT).commit()
            }
        }
    }
}