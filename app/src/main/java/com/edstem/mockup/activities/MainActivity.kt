package com.edstem.mockup.activities

import android.os.Bundle
import android.view.MenuItem
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
import com.edstem.mockup.fragments.ComingSoonFragment
import com.edstem.mockup.fragments.HomeFragment
import com.edstem.mockup.hideSystemBars
import com.edstem.mockup.toast
import com.google.android.material.navigation.NavigationView

/**
 * This is main activity for this application, designed to house the navigation options using the
 * side menu or the bottom menu.
 *
 * Additionally this screen also contains, what I assume to be the notification badges, on the
 * top-right of the screen
 **/
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment      : HomeFragment
    private lateinit var familyFragment    : ComingSoonFragment
    private lateinit var activityFragment  : ComingSoonFragment
    private lateinit var contributeFragment: ComingSoonFragment

    private lateinit var binding            : ActivityMainBinding
    private lateinit var notificationAdapter: OverlappingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
        showNotificationBadges()
        binding.mainContent.bottomNav.selectedItemId = R.id.home_menu
    }

    override fun onResume() {
        super.onResume()
        hideSystemBars()
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
                    R.id.family -> {
                        if(!this@MainActivity::familyFragment.isInitialized){
                            familyFragment = ComingSoonFragment.newInstance("Family")
                        }
                        loadFragment(familyFragment)
                        this@MainActivity.toast("Coming Soon")
                    }
                    R.id.activity -> {
                        if(!this@MainActivity::activityFragment.isInitialized){
                            activityFragment = ComingSoonFragment.newInstance("Activity")
                        }
                        loadFragment(activityFragment)
                        this@MainActivity.toast("Coming Soon")
                    }
                    R.id.contribute -> {
                        if(!this@MainActivity::contributeFragment.isInitialized){
                            contributeFragment = ComingSoonFragment.newInstance("Contribute")
                        }
                        loadFragment(contributeFragment)
                        this@MainActivity.toast("Coming Soon")
                    }
                }
                return@setOnItemSelectedListener true
            }

            navDrawerSetup()

            mainContent.menuBtn.setOnClickListener {
                binding.drawer.open()
            }
        }
    }

    /**
     * setting up the nav drawer
     **/
    private fun navDrawerSetup() {
        val toggle = ActionBarDrawerToggle(
            this@MainActivity, binding.drawer, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.sideMenu.setNavigationItemSelectedListener(this@MainActivity)
    }

    /**
     * performs the transactions for loading the supplied fragment into the fragment container
     *
     * @param fragment
     */
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