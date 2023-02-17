package com.novuspax.androidvisionboard.contentProvider.room

import android.content.Context
import androidx.room.*
import com.novuspax.androidvisionboard.contentProvider.model.Dream
import com.novuspax.androidvisionboard.contentProvider.room.dao.DreamDao

@Database(version = 1, entities = [Dream::class])
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getDreamDao(): DreamDao

    companion object {

        private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME).build()
            }
            return INSTANCE!!
        }

        const val DATABASE_NAME = "dream_db"
    }
}