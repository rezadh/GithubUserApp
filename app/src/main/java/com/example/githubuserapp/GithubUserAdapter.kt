package com.example.githubuserapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_githubuser.view.*


class GithubUserAdapter(private val listGithubUser: ArrayList<GithubUser>) : RecyclerView.Adapter<GithubUserAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_githubuser, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listGithubUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listGithubUser[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(githubuser : GithubUser){
            with(itemView){
                tv_item_username.text = githubuser.username
                tv_item_name.text = githubuser.name
                tv_item_company.text = githubuser.company
                tv_item_location.text = githubuser.location
                tv_item_repository.text = githubuser.repository
                tv_item_follower.text = githubuser.follower
                tv_item_following.text = githubuser.following
                img_item_photo.setImageResource(githubuser.avatar!!)
            }
        }
    }

}
//class GithubUserAdapter internal constructor(private val context: Context) : BaseAdapter(){
//    internal var githubusers = arrayListOf<GithubUser>()
//
//    override fun getItem(i: Int): Any = githubusers[i]
//
//    override fun getItemId(i: Int): Long = i.toLong()
//
//    override fun getCount(): Int = githubusers.size
//
//    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
//        var itemView = view
//        if(itemView == null){
//            itemView = LayoutInflater.from(context).inflate(R.layout.item_row_githubuser, viewGroup, false)
//        }
//        val viewHolder = ViewHolder(itemView as View)
//        val githubuser = getItem(position) as GithubUser
//        viewHolder.bind(githubuser)
//        return itemView
//    }
//
//    private inner class ViewHolder constructor(private val view: View){
//        internal fun bind(githubuser : GithubUser){
//            with(view){
//                tv_item_username.text = githubuser.username
//                tv_item_name.text = githubuser.name
//                tv_item_company.text = githubuser.company
//                tv_item_location.text = githubuser.location
//                tv_item_repository.text = githubuser.repository
//                tv_item_follower.text = githubuser.follower
//                tv_item_following.text = githubuser.following
//                img_item_photo.setImageResource(githubuser.avatar!!)
//            }
//        }
//    }
//}