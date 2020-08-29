package com.example.githubuserapp.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.githubuserapp"
    const val SCHEME = "content"
    internal class FavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_FAVORITE_NAME = "user"
            const val USERNAME = "username"
            const val NAME = "name"
            const val AVATAR = "avatar"
            const val COMPANY = "company"
            const val LOCATION = "location"
            const val REPOSITORY = "repository"
            const val FOLLOWER = "follower"
            const val FOLLOWING = "following"
            const val GISTS = "gists"
            const val GITHUBADDRESS = "githubaddress"
            const val FAVORITE = "favorite"
            val CONTENT_URI : Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORITE_NAME)
                .build()
        }
    }
}