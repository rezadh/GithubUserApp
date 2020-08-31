package com.example.githubuserapp.view

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.AVATAR
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.COMPANY
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FAVORITE
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWER
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWING
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.GISTS
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.GITHUBADDRESS
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.LOCATION
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.NAME
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.REPOSITORY
import com.example.githubuserapp.db.DatabaseContract.FavoriteColumns.Companion.USERNAME
import com.example.githubuserapp.db.FavoriteHelper
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.MainViewModel
import com.example.githubuserapp.viewModel.SectionPagerAdapter

import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var favoriteHelper: FavoriteHelper
    private var statusFavorite = false
    private lateinit var mainViewModel: MainViewModel
    private var githubUser: GithubUser? = null
    companion object {
        const val EXTRA_USER = "extra_user"
    }
 //testing
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val sectionsPagerAdapter =
            SectionPagerAdapter(
                this,
                supportFragmentManager
            )
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        val actionbar = supportActionBar
        actionbar?.title = getString(R.string.detail_user)
        favoriteHelper = FavoriteHelper.getInstance(applicationContext)
        favoriteHelper.open()
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
       githubUser = intent.getParcelableExtra(EXTRA_USER)
        val cursor: Cursor = favoriteHelper.queryByUsername(githubUser?.username.toString())
        if (cursor.moveToNext()){
            statusFavorite = true
            isFavorite(true)
        }
        setData()
        fab_favorite.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {

        startActivity(Intent(this@DetailActivity, MainActivity::class.java))
    }

    override fun onResume() {
        mainViewModel.getDataGit(applicationContext)
        super.onResume()
    }

    private fun isFavorite(status: Boolean) {
        if (status){
            fab_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            fab_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun setData(){
        githubUser = intent.getParcelableExtra(EXTRA_USER)
        val tvname: TextView = findViewById(R.id.tv_detail_name)
        tvname.text = githubUser?.name
        tv_detail_username.text = githubUser?.username
        tv_detail_company.text = githubUser?.company
        tv_detail_location.text = githubUser?.location
        tv_detail_repositori.text = githubUser?.repository
        tv_detail_follower.text = githubUser?.follower
        tv_detail_following.text = githubUser?.following
        tv_detail_gists.text = githubUser?.gists
        tv_detail_githubaddress.text = githubUser?.githubAddress
        Glide.with(this)
            .load(githubUser?.avatar)
            .into(img_detail_photo)
    }

    override fun onClick(v: View?) {
        githubUser = intent.getParcelableExtra(EXTRA_USER)
        when(v?.id){
            R.id.fab_favorite -> {
                if (statusFavorite) {
                    val idUser = githubUser?.username.toString()
                    favoriteHelper.deleteById(idUser)
                    Toast.makeText(this, getString(R.string.fab_fav_delete), Toast.LENGTH_SHORT).show()
                    isFavorite(false)
                    statusFavorite = true
                } else {
                    val values = ContentValues()
                    values.put(USERNAME, githubUser?.username)
                    values.put(NAME, githubUser?.name)
                    values.put(COMPANY, githubUser?.company)
                    values.put(LOCATION, githubUser?.location)
                    values.put(FOLLOWER, githubUser?.follower)
                    values.put(FOLLOWING, githubUser?.following)
                    values.put(AVATAR, githubUser?.avatar)
                    values.put(REPOSITORY, githubUser?.repository)
                    values.put(GISTS, githubUser?.gists)
                    values.put(GITHUBADDRESS, githubUser?.githubAddress)
                    values.put(FAVORITE, "favorite")

                    statusFavorite = false
                    contentResolver.insert(CONTENT_URI, values)
                    Toast.makeText(this, getString(R.string.fab_fav_insert), Toast.LENGTH_SHORT).show()
                    isFavorite(true)
                }
            }
        }
    }
}