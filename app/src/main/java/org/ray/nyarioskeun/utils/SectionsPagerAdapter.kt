package org.ray.nyarioskeun.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.ui.history.BrokenFragment
import org.ray.nyarioskeun.ui.history.FixedFragment
import org.ray.nyarioskeun.ui.history.ProgressFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_broken,
    R.string.tab_process,
    R.string.tab_fixed
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int) = context.resources.getString(TAB_TITLES[position])

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> BrokenFragment()
        1 -> ProgressFragment()
        2 -> FixedFragment()
        else -> Fragment()
    }

    override fun getCount() = 3
}