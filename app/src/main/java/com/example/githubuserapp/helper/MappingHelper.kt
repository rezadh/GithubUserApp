package com.example.githubuserapp.helper

import android.database.Cursor
import com.example.githubuserapp.db.DatabaseContract
import com.example.githubuserapp.model.GithubUserFavorite

object MappingHelper {
    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<GithubUserFavorite>{
        val userList = ArrayList<GithubUserFavorite>()

        userCursor?.apply {
            while (moveToNext()){
                val username = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR))
                val company = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.COMPANY))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION))
                val repository = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.REPOSITORY))
                val follower = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWER))
                val following = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWING))
                val gists = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.GISTS))
                val githubAddress = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.GITHUBADDRESS))
                val favorite = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FAVORITE))
                userList.add(GithubUserFavorite(username, name, avatar, company, location, repository, follower, following, gists, githubAddress, favorite))
            }
        }
        return userList
    }

}