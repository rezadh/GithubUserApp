package com.example.githubuserapp.viewModel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.model.GithubUserFavorite
import com.example.githubuserapp.view.DetailActivity
import kotlinx.android.synthetic.main.item_row_githubuser.view.*
import java.util.ArrayList

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
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
        val data = listFavorites[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = GithubUser(
                data.username,
                data.name,
                data.avatar,
                data.company,
                data.location,
                data.repository,
                data.follower,
                data.following,
                data.gists,
                data.githubAddress
            )
            val mIntent = Intent(it.context, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_USER, dataUserIntent)
            it.context.startActivity(mIntent)
        }
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