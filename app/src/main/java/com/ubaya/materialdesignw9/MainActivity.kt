package com.ubaya.materialdesignw9

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ubaya.materialdesignw9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val fragments: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragments.add(HomeFragment())
        fragments.add(PlaylistFragment())
        fragments.add(ProfilesFragment())
        binding.viewPager.adapter = MyAdapter(this, fragments)

        binding.viewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.selectedItemId =
                    binding.bottomNav.menu.getItem(position).itemId
            }
        }
        )

        binding.bottomNav.setOnItemSelectedListener {
            binding.viewPager.currentItem = when(it.itemId) {
                R.id.itemHome -> 0
                R.id.itemPlaylist -> 1
                R.id.itemProfile -> 2
                else -> 0 // default to home
            }
            true
        }
    }
}