package com.example.favoriteapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserFavorite(
    var username: String? = "",
    var name: String? = "",
    var avatar: String? = "",
    var company: String? = "",
    var location: String? = "",
    var repository: String? = "",
    var follower: String? = "",
    var following: String? = "",
    var gists: String? = "",
    var githubAddress: String? = "",
    var isFav: String? = ""
) : Parcelable