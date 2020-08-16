package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
//        `val actionbar = supportActionBar
        val tvname: TextView = findViewById(R.id.tv_detail_name)
        val githubData = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser

//
        tvname.text = githubData.name
//        actionbar!!.title = githubData.name
//        actionbar.setDisplayHomeAsUpEnabled(true)
        tv_detail_username.text = githubData.username
        tv_detail_company.text = githubData.company
        tv_detail_location.text = githubData.location
        tv_detail_repositori.text = githubData.repository
        tv_detail_follower.text = githubData.follower
        tv_detail_following.text = githubData.following
        Glide.with(this)
            .load(githubData.avatar)
            .into(img_detail_photo)
//        img_detail_photo.setImageResource(githubData.avatar!!)`
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}