package com.example.favoriteapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.favoriteapp.db.DatabaseContract
import com.example.favoriteapp.db.DatabaseContract.AUTHORITY
import com.example.favoriteapp.db.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.example.favoriteapp.db.DatabaseContract.FavoriteColumns.Companion.TABLE_FAVORITE_NAME
import com.example.favoriteapp.db.FavoriteHelper

class MyProvider : ContentProvider() {
    companion object {
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favoriteHelper: FavoriteHelper
    }
    init {
        // content://com.dicoding.picodiploma.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, TABLE_FAVORITE_NAME, FAVORITE)
        // content://com.dicoding.picodiploma.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY,
            "$TABLE_FAVORITE_NAME/#",
            FAVORITE_ID)
    }


    override fun onCreate(): Boolean {
        favoriteHelper = FavoriteHelper.getInstance(context as Context)
        favoriteHelper.open()
        return true
    }
    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor?
        when(sUriMatcher.match(uri)){
            FAVORITE -> cursor = favoriteHelper.queryAll()
            FAVORITE_ID -> cursor = favoriteHelper.queryByUsername(uri.lastPathSegment.toString())
            else -> cursor = null
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val added: Long = when(FAVORITE){
            sUriMatcher.match(uri) -> favoriteHelper.insert(contentValues)
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return Uri.parse("$CONTENT_URI/$added")
    }
    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        val updated: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> favoriteHelper.update(uri.lastPathSegment.toString(),contentValues)
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return updated
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        val deleted: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> favoriteHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return deleted
    }
}