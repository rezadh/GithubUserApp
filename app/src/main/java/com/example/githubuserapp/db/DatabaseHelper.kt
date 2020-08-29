package com.example.githubuserapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.AVATAR
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.COMPANY
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FAVORITE
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWER
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWING
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.GISTS
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.GITHUBADDRESS
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.LOCATION
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.NAME
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.REPOSITORY
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.TABLE_FAVORITE_NAME
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.USERNAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "dbgithubuser"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_GITHUB_USER = "CREATE TABLE $TABLE_FAVORITE_NAME" +
                "($USERNAME TEXT NOT NULL," +
                "$NAME TEXT NOT NULL," +
                "$AVATAR TEXT NOT NULL," +
                "$COMPANY TEXT NOT NULL," +
                "$LOCATION TEXT NOT NULL," +
                "$REPOSITORY TEXT NOT NULL," +
                "$FOLLOWER TEXT NOT NULL," +
                "$FOLLOWING TEXT NOT NULL," +
                "$GISTS TEXT NOT NULL," +
                "$GITHUBADDRESS TEXT NOT NULL," +
                "$FAVORITE TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_GITHUB_USER)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVORITE_NAME")
        onCreate(db)
    }
}