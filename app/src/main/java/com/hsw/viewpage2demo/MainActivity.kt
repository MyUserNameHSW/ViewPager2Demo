package com.hsw.viewpage2demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.google.android.material.tabs.TabLayoutMediator
import com.hsw.viewpage2demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        val adapter = Adapter(this, 4)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, positon ->
            tab.text = "Tab$positon"
        }.attach()
        binding.add.setOnClickListener {
            val itemCount = adapter.itemCount
            val next = itemCount + 1
            adapter.fragmentSize = next
            adapter.notifyDataSetChanged()
        }

        binding.reduce.setOnClickListener {
            val itemCount = adapter.itemCount
            if (itemCount > 1) {
                val next = itemCount - 1
                adapter.fragmentSize = next
                adapter.notifyDataSetChanged()
            }
        }
        binding.viewPager.offscreenPageLimit = 10
    }


    inner class Adapter(fragmentActivity: FragmentActivity, var fragmentSize: Int): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return fragmentSize
        }

        override fun createFragment(position: Int): Fragment {
            return BlankFragment.newInstance("$position", "fragment")
        }
    }
}