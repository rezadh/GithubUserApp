package com.example.githubuserapp.viewModel

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubuserapp.view.fragment.FollowerFragment
import com.example.githubuserapp.view.fragment.FollowingFragment
import com.example.githubuserapp.R

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    @StringRes
    private val tabTitles = intArrayOf(
        R.string.follower,
        R.string.following
    )
    private val pages = listOf(
        FollowerFragment(),
        FollowingFragment()
    )
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                FollowerFragment()
            1 -> fragment =
                FollowingFragment()
        }
        return fragment as Fragment
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }
    override fun getCount(): Int {
        return pages.size
    }

}