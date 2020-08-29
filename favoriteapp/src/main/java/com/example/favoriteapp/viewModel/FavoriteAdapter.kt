package com.example.favoriteapp.viewModel

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.favoriteapp.R
import com.example.favoriteapp.model.GithubUser
import com.example.favoriteapp.model.GithubUserFavorite
import kotlinx.android.synthetic.main.item_row_githubuser.view.*
import java.util.ArrayList

class FavoriteAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var listFavorites = ArrayList<GithubUserFavorite>()
        set(listFavorites) {
            if (listFavorites.size > 0) {
                this.listFavorites.clear()
            }
            this.listFavorites.addAll(listFavorites)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_githubuser, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])

    }

    override fun getItemCount(): Int = this.listFavorites.size

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(githubUserFav : GithubUserFavorite){
            with(itemView){
                tv_item_username.text = githubUserFav.username
                tv_item_github.text = githubUserFav.githubAddress
                Glide.with(itemView.context)
                    .load(githubUserFav.avatar)
                    .apply(RequestOptions().override(width, height))
                    .into(img_item_photo)
            }
        }
    }
}