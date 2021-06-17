package com.example.coroutines_example.demo_view_pager_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coroutines_example.demo_view_pager_2.fragment.FirstFragment
import com.example.coroutines_example.demo_view_pager_2.fragment.SecondFragment
import com.example.coroutines_example.demo_view_pager_2.fragment.ThirdFragment

class ViewPager2Adapter(private val fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            3 -> ThirdFragment()
            else -> FirstFragment()
        }
    }
}