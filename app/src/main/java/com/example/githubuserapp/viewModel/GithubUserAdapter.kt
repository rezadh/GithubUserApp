package com.example.githubuserapp.viewModel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.DetailActivity
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import kotlinx.android.synthetic.main.item_row_githubuser.view.*


class GithubUserAdapter(private val listGithubUser: ArrayList<GithubUser>) : RecyclerView.Adapter<GithubUserAdapter.ListViewHolder>() {
    fun setData(items: ArrayList<GithubUser>) {
        listGithubUser.clear()
        listGithubUser.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_githubuser, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listGithubUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listGithubUser[position])

        val data = listGithubUser[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = GithubUser(
                data.username,
                data.name,
                data.avatar,
                data.company,
                data.location,
                data.repository,
                data.follower,
                data.following
            )
            val mIntent = Intent(it.context, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_USER, dataUserIntent)
            it.context.startActivity(mIntent)
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(githubuser : GithubUser){
            with(itemView){
                tv_item_username.text = githubuser.username
                tv_item_name.text = githubuser.name
                tv_item_company.text = githubuser.company
                tv_item_location.text = githubuser.location
                Glide.with(itemView.context)
                    .load(githubuser.avatar)
                    .apply(RequestOptions().override(95, 95))
                    .into(img_item_photo)
            }
        }
    }
}