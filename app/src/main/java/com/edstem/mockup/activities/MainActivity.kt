package com.edstem.mockup.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.edstem.mockup.R
import com.edstem.mockup.databinding.ActivityMainBinding
import com.edstem.mockup.fragments.HomeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
        binding.bottomNav.selectedItemId = R.id.home_menu
    }

    private fun bindListeners() {
        with(binding){
            bottomNav.setOnItemReselectedListener { item ->
                when(item.itemId){
                    R.id.home_menu -> {
                        if (!this@MainActivity::homeFragment.isInitialized) {
                            homeFragment = HomeFragment()
                        }
                        loadFragment(homeFragment)
                    }
                    R.id.family -> {}
                    R.id.activity -> {}
                    R.id.contribute -> {}
                }
            }
        }
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}