package com.novuspax.androidvisionboard.visionBoard.model

import java.util.*

data class Board(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val imageUrl: Int
)