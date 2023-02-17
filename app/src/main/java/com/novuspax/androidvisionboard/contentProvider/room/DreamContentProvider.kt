package com.novuspax.androidvisionboard.contentProvider.room

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.novuspax.androidvisionboard.contentProvider.model.Dream
import com.novuspax.androidvisionboard.contentProvider.model.Dream.Companion.DREAM_ID
import com.novuspax.androidvisionboard.contentProvider.room.dao.DreamDao

class DreamContentProvider : ContentProvider() {

    // uri example  -->  //content://com.novuspax.androidvisionboard/dreamTable/dream
    private var dreamDao: DreamDao? = null
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, DREAM_TABLE, DREAMS)
        uriMatcher.addURI(AUTHORITY, "$DREAM_TABLE/#", DREAM_ID) // hash tag means we're getting data of particular id from content provider
    }

    override fun onCreate(): Boolean {
        dreamDao = ApplicationDatabase.getInstance(context!!).getDreamDao()
        return false
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            DREAMS -> {
                cursor = dreamDao?.findAll()
                context?.let {
                    cursor?.setNotificationUri(it.contentResolver, uri)
                    return cursor
                }
            }
            else -> {
                throw java.lang.Exception("")
            }
        }
        throw java.lang.Exception("")
    }

    // we can use this to check and return mime type
    override fun getType(uri: Uri): String {
        when (uriMatcher.match(uri)) {
            DREAMS -> {
                return "vnd.android.cursor.dir/$DREAM_TABLE"
            }
            else -> {
                throw java.lang.Exception("")
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            DREAMS -> {
                context?.let {
                    val id = dreamDao?.addDream(Dream.fromContentValues(values))
                    if (id != 0L) {
                        it.contentResolver.notifyChange(uri, null)
                    }
                }
            }
            DREAM_ID -> {
                throw java.lang.Exception("")
            }
            else -> {
                throw java.lang.Exception("")
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when (uriMatcher.match(uri)) {
            DREAMS -> {
                context?.let {
                    val count = dreamDao?.deleteAll()
                    it.contentResolver.notifyChange(uri, null)
                    return count!!
                }
            }
            DREAM_ID -> {
                context?.let {
                    val count = dreamDao?.delete(ContentUris.parseId(uri))
                    it.contentResolver.notifyChange(uri,null)
                }
            }
            else -> {
                throw java.lang.Exception("")
            }
        }
        throw java.lang.Exception("")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0

    companion object {
        const val AUTHORITY = "com.novuspax.androidvisionboard"
        const val DREAM_TABLE = "dreamTable"
        const val NAME = "name"
        const val ID = "id"
        const val DESCRIPTION = "description"
        const val DREAMS = 1
        const val DREAM_ID = 2
        const val URL = "content://$AUTHORITY/$DREAM_TABLE"
    }
}
