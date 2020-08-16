
package com.example.githubuserapp

import android.app.SearchManager
import android.content.Context
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.GithubUserAdapter
import com.example.githubuserapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    private var listDataUser: ArrayList<GithubUser> = ArrayList()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter : GithubUserAdapter
//    private lateinit var dataUsername: Array<String>
//    private lateinit var dataName: Array<String>
//    private lateinit var dataCompany: Array<String>
//    private lateinit var dataLocation: Array<String>
//    private lateinit var dataFollower: Array<String>
//    private lateinit var dataFollowing: Array<String>
//    private lateinit var dataRepository: Array<String>
//    private lateinit var dataPhoto: TypedArray
//    private var githubusers = arrayListOf<GithubUser>()
//    private val list = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = GithubUserAdapter(listDataUser)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        rv_list.setHasFixedSize(true)
        searchData()
        viewConfig()
        runGetDataGit()
        configMainViewModel(adapter)
//        list.addAll(getListGithubUser())
//        showRecyclerList()
    }

//    private fun showRecyclerList() {
//        rv_list.layoutManager = LinearLayoutManager(this)
//        val listGithubUserAdapter =
//            GithubUserAdapter(list)
//        rv_list.adapter = listGithubUserAdapter
//    }

//    fun getListGithubUser() : ArrayList<GithubUser>{
//        dataUsername = resources.getStringArray(R.array.username)
//        dataName = resources.getStringArray(R.array.name)
//        dataPhoto = resources.obtainTypedArray(R.array.avatar)
//        dataCompany = resources.getStringArray(R.array.company)
//        dataLocation = resources.getStringArray(R.array.location)
//        dataRepository = resources.getStringArray(R.array.repository)
//        dataFollower = resources.getStringArray(R.array.followers)
//        dataFollowing = resources.getStringArray(R.array.following)
//        for (position in dataName.indices) {
//            val githubuser = GithubUser(
//                dataUsername[position],
//                dataName[position],
//                dataPhoto[position],
//                dataCompany[position],
//                dataLocation[position],
//                dataRepository[position],
//                dataFollower[position],
//                dataFollowing[position]
//            )
//            githubusers.add(githubuser)
//        }
//        return githubusers
//    }

    private fun viewConfig() {
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.setHasFixedSize(true)

        adapter.notifyDataSetChanged()
        rv_list.adapter = adapter
    }

    private fun runGetDataGit() {
        mainViewModel.getDataGit(applicationContext)
        showLoading(true)
    }


    private fun configMainViewModel(adapter: GithubUserAdapter) {
        mainViewModel.getListUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgress.visibility = View.VISIBLE
        } else {
            loadingProgress.visibility = View.INVISIBLE
        }
    }

    private fun searchData() {
        user_search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    listDataUser.clear()
                    viewConfig()
                    mainViewModel.getDataGitSearch(newText, applicationContext)
                    showLoading(true)
                    configMainViewModel(adapter)
                } else {
                    return true
                }
                return true
            }

        })
    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.option_menu, menu)
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search).actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.queryHint = resources.getString(R.string.search_hint)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
//                return true
//            }
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
//        return true
//    }
}