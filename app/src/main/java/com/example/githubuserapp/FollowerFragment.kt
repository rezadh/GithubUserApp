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
import com.example.githubuserapp.viewModel.ListDataFollowerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.android.synthetic.main.fragment_following.*

class FollowerFragment : Fragment() {
    private val listData: ArrayList<GithubUser> = ArrayList()
    private lateinit var adapter: ListDataFollowerAdapter
    private lateinit var followerViewModel: FollowerViewModel

    companion object {
        val TAG = FollowerFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListDataFollowerAdapter(listData)
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowerViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser
        rv_follower_fragment.layoutManager = LinearLayoutManager(activity)
        rv_follower_fragment.setHasFixedSize(true)
        rv_follower_fragment.adapter = adapter

        followerViewModel.getDataGit(activity!!.applicationContext, dataUser.username.toString())

        followerViewModel.getListFollower().observe(activity!!, Observer { listFollower ->
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