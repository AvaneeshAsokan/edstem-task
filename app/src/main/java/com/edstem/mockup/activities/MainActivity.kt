package com.edstem.mockup.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edstem.mockup.R
import com.edstem.mockup.adapters.OverlappingItemAdapter
import com.edstem.mockup.adapters.OverlappingItemDecoration
import com.edstem.mockup.data.UserNotification
import com.edstem.mockup.databinding.ActivityMainBinding
import com.edstem.mockup.fragments.HomeFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var notificationAdapter: OverlappingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
        showNotificationBadges()
        binding.mainContent.bottomNav.selectedItemId = R.id.home_menu
    }

    private fun showNotificationBadges() {
        notificationAdapter = OverlappingItemAdapter(this)
        val notificationList = listOf(
            UserNotification(R.drawable.notification_item1),
            UserNotification(R.drawable.notification_item2),
        )

        notificationAdapter.setData(notificationList)
        with(binding.mainContent.notificationBadges){
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            addItemDecoration(OverlappingItemDecoration(-30))
            adapter = notificationAdapter
        }
    }

    private fun bindListeners() {
        with(binding){
            mainContent.bottomNav.setOnItemSelectedListener { item ->
                when(item.itemId){
                    R.id.home_menu -> {
                        if (!this@MainActivity::homeFragment.isInitialized) {
                            homeFragment = HomeFragment()
                        }
                        loadFragment(homeFragment)
                    }
                    R.id.family,
                    R.id.activity,
                    R.id.contribute -> {
                        Toast.makeText(this@MainActivity, "Coming soon", Toast.LENGTH_SHORT).show()
                    }
                }
                return@setOnItemSelectedListener false
            }

            val toggle = ActionBarDrawerToggle(
                this@MainActivity, binding.drawer, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )

            drawer.addDrawerListener(toggle)
            toggle.syncState()
            sideMenu.setNavigationItemSelectedListener(this@MainActivity)
            mainContent.menuBtn.setOnClickListener {
                binding.drawer.open()
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainContent.fragmentContainer.id, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}