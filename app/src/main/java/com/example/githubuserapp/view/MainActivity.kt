
package com.example.githubuserapp.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.GithubUserAdapter
import com.example.githubuserapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
    private var listDataUser: ArrayList<GithubUser> = ArrayList()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter : GithubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = GithubUserAdapter(listDataUser)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        viewConfig()
        runGetDataGit()
        configMainViewModel(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchMenuItem = menu.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {

                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                listDataUser.clear()
                rv_list.removeAllViews()
                viewConfig()
                showLoading(true)
                mainViewModel.getDataGit(applicationContext)

                return true
            }
        })

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listDataUser.clear()
                    rv_list.removeAllViews()
                    viewConfig()
                    showLoading(true)
                    mainViewModel.getDataGitSearch(query, applicationContext)
                    searchView.onActionViewExpanded()
                    searchView.clearFocus()
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

        })

    return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val mIntent = Intent(this, SettingsActivity::class.java)
                startActivity(mIntent)
            }
            R.id.favorite -> {
                val mIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
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

    override fun onResume() {
        mainViewModel.getDataGit(applicationContext)
        super.onResume()
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
}