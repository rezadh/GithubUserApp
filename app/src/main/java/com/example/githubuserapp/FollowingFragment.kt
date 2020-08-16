package com.example.githubuserapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.FollowerViewModel
import com.example.githubuserapp.viewModel.FollowingViewModel
import com.example.githubuserapp.viewModel.ListDataFollowerAdapter
import com.example.githubuserapp.viewModel.ListDataFollowingAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    private val listData: ArrayList<GithubUser> = ArrayList()
    private lateinit var adapter: ListDataFollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel
    companion object {
        val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListDataFollowingAdapter(listData)

        followingViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        val dataUser = activity!!.intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser
        rv_following_fragment.layoutManager = LinearLayoutManager(activity)
        rv_following_fragment.setHasFixedSize(true)
        rv_following_fragment.adapter = adapter

        followingViewModel.getDataGit(activity!!.applicationContext, dataUser.username.toString())
        followingViewModel.getListFollowing().observe(activity!!, Observer { listFollower ->
            if (listFollower != null) {
                adapter.setData(listFollower)
                showLoading(false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgressFollowing.visibility = View.VISIBLE
        } else {
            loadingProgressFollowing.visibility = View.INVISIBLE
        }
    }
}