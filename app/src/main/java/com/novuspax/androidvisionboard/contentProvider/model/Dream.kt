package com.novuspax.androidvisionboard.contentProvider.model

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "dreamTable")
data class Dream(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(DREAM_NAME) var name: String?,
    @ColumnInfo(DREAM_DESCRIPTION) val description: String?,
) {
    fun fromContentValues(cv: ContentValues): Dream {
        var dreamId: Int? = null
        var dreamName: String = ""
        var dreamDescription: String = ""

        if (cv.containsKey(DREAM_ID)) {
            dreamId = cv.getAsInteger(DREAM_ID)
        }
        if (cv.containsKey(DREAM_NAME)) {
            dreamName = cv.getAsString(DREAM_NAME)
        }
        if (cv.containsKey(DREAM_DESCRIPTION)) {
            dreamDescription = cv.getAsString(DREAM_DESCRIPTION)
        }
        return Dream(id = dreamId, name = dreamName, description = dreamDescription)
    }

    companion object {
        const val DREAM_ID = "id"
        const val DREAM_NAME = "name"
        const val DREAM_DESCRIPTION = "description"
    }
}
