package com.novuspax.androidvisionboard.visionBoard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novuspax.androidvisionboard.databinding.InflaterBoardItemBinding
import com.novuspax.androidvisionboard.visionBoard.model.Board

class BoardRecyclerViewAdapter(
    private val boardList: List<Board>,
    val onClick: (View, Int) -> Unit
) : RecyclerView.Adapter<BoardRecyclerViewAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                boardList[position].let {
                    tvBoard.text = it.name
                    imgBoard.setImageResource(it.imageUrl)
                    this.root.setOnClickListener {
                        onClick(this.root, position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            InflaterBoardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = boardList.size

    class MyViewHolder(val binding: InflaterBoardItemBinding) : RecyclerView.ViewHolder(binding.root)
}