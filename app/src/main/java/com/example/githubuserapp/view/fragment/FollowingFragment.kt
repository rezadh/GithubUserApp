package com.example.githubuserapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.FollowingViewModel
import com.example.githubuserapp.viewModel.ListDataFollowingAdapter
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    private val listData: ArrayList<GithubUser> = ArrayList()
    private lateinit var adapter: ListDataFollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel
    companion object {
        val TAG: String = FollowingFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListDataFollowingAdapter(listData)

        followingViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        val dataUser = requireActivity().intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser
        showLoading(true)

        rv_following_fragment.layoutManager = LinearLayoutManager(activity)
        rv_following_fragment.setHasFixedSize(true)
        rv_following_fragment.adapter = adapter

        followingViewModel.getDataGit(requireActivity().applicationContext, dataUser.username.toString())
        followingViewModel.getListFollowing().observe(requireActivity(), Observer { listFollower ->
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