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
import com.example.githubuserapp.viewModel.FollowerViewModel
import com.example.githubuserapp.viewModel.ListDataFollowerAdapter
import kotlinx.android.synthetic.main.fragment_follower.*

class FollowerFragment : Fragment() {
    private val listData: ArrayList<GithubUser> = ArrayList()
    private lateinit var adapter: ListDataFollowerAdapter
    private lateinit var followerViewModel: FollowerViewModel
    private var dataUser: GithubUser? = null
    companion object {
        val TAG: String = FollowerFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListDataFollowerAdapter(listData)
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowerViewModel::class.java)

        dataUser = requireActivity().intent.getParcelableExtra(EXTRA_USER)
        rv_follower_fragment.layoutManager = LinearLayoutManager(activity)
        rv_follower_fragment.setHasFixedSize(true)
        rv_follower_fragment.adapter = adapter

        followerViewModel.getDataGit(requireActivity().applicationContext, dataUser?.username.toString())
        showLoading(true)
        followerViewModel.getListFollower().observe(requireActivity(), Observer { listFollower ->
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
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgressFollower.visibility = View.VISIBLE
        } else {
            loadingProgressFollower.visibility = View.INVISIBLE
        }
    }
}