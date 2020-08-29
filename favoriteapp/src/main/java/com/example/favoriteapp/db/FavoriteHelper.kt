package com.example.favoriteapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.ContactsContract
import com.example.favoriteapp.db.DatabaseContract.FavoriteColumns.Companion.TABLE_FAVORITE_NAME
import com.example.favoriteapp.db.DatabaseContract.FavoriteColumns.Companion.USERNAME
import java.sql.SQLException
import java.util.ArrayList

class FavoriteHelper(context: Context) {

    private lateinit var database: SQLiteDatabase
    private  var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    companion object{
        private const val DATABASE_TABLE = TABLE_FAVORITE_NAME
        private var INSTANCE: FavoriteHelper? = null

        fun getInstance(context: Context): FavoriteHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: FavoriteHelper(context)
        }
    }
    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun queryByUsername(username: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$USERNAME = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$USERNAME = '$id'", null)
    }

}