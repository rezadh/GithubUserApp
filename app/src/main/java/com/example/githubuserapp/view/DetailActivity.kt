package com.example.githubuserapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.viewModel.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }
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

        val tvname: TextView = findViewById(R.id.tv_detail_name)
        val githubData = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser

        tvname.text = githubData.name
        tv_detail_username.text = githubData.username
        tv_detail_company.text = githubData.company
        tv_detail_location.text = githubData.location
        tv_detail_repositori.text = githubData.repository
        tv_detail_follower.text = githubData.follower
        tv_detail_following.text = githubData.following
        tv_detail_gists.text = githubData.gists
        tv_detail_githubaddress.text = githubData.githubaddress
        Glide.with(this)
            .load(githubData.avatar)
            .into(img_detail_photo)

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
}