package com.novuspax.androidvisionboard.contentProvider.room.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.novuspax.androidvisionboard.contentProvider.model.Dream

@Dao
interface DreamDao {

    @Insert
    fun addDream(dream: Dream): Long

    // it also returns cursor because behind the scene it is SQLite it self
    @Query("SELECT * FROM dreamTable")
    fun findAll(): Cursor

    @Query("DELETE FROM dreamTable WHERE id = :id")
    fun delete(id: Long): Int

    @Query("DELETE FROM dreamTable")
    fun deleteAll(): Int

    @Update
    fun update(dream: Dream)

}