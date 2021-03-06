package com.example.githubuserapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import kotlinx.android.synthetic.main.item_row_githubuser.view.*

class ListDataFollowingAdapter(private val listDataFollowing: ArrayList<GithubUser>) : RecyclerView.Adapter<ListDataFollowingAdapter.ListViewHolder>() {
    fun setData(items: ArrayList<GithubUser>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_githubuser, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDataFollowing.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listDataFollowing[position])
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(githubuser : GithubUser){
            with(itemView){
                tv_item_username.text = githubuser.username
                tv_item_github.text = githubuser.githubAddress
                Glide.with(itemView.context)
                    .load(githubuser.avatar)
                    .apply(RequestOptions().override(GithubUserAdapter.width, GithubUserAdapter.height))
                    .into(img_item_photo)
            }
        }
    }
}