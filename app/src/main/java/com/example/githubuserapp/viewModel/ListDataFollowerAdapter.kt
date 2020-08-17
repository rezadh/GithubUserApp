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

class ListDataFollowerAdapter(private val listDataFollower: ArrayList<GithubUser>) : RecyclerView.Adapter<ListDataFollowerAdapter.ListViewHolder>() {
    fun setData(items: ArrayList<GithubUser>) {
        listDataFollower.clear()
        listDataFollower.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_githubuser, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDataFollower.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listDataFollower[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(githubuser : GithubUser){
            with(itemView){
                tv_item_username.text = githubuser.username
                tv_item_github.text = githubuser.githubaddress
                Glide.with(itemView.context)
                    .load(githubuser.avatar)
                    .apply(RequestOptions().override(95, 95))
                    .into(img_item_photo)
            }
        }
    }
}