
package com.example.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter : GithubUserAdapter
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var githubusers = arrayListOf<GithubUser>()
    private val list = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list.setHasFixedSize(true)
        list.addAll(getListGithubUser())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rv_list.layoutManager = LinearLayoutManager(this)
        val listGithubUserAdapter = GithubUserAdapter(list)
        rv_list.adapter = listGithubUserAdapter
    }

    fun getListGithubUser() : ArrayList<GithubUser>{
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        for (position in dataName.indices) {
            val githubuser = GithubUser(
                dataUsername[position],
                dataName[position],
                dataPhoto.getResourceId(position, 0),
                dataCompany[position],
                dataLocation[position],
                dataRepository[position],
                dataFollower[position],
                dataFollowing[position]
            )
            githubusers.add(githubuser)
        }
        return githubusers
    }
}