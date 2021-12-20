package com.example.marketplaceproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.marketplaceproject.repository.Repository
import com.example.marketplaceproject.viewModels.profile.ProfileViewModel
import com.example.marketplaceproject.viewModels.profile.ProfileViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var searchMenuItem: MenuItem
    private lateinit var filterMenuItem: MenuItem
    private lateinit var profileMenuItem: MenuItem
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createProfileViewModel()
        initializeToolBar()
        initializeNavigation()
        setupNavigationMenuVisibility()
        setupToolbar()

        //hogyha megnyomjuk a toolbaron a visszafele gombot, akkor visszamegyunk a korabbi ablakra
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun createProfileViewModel() {
        val factory = ProfileViewModelFactory(Repository())
        profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
    }

    //-----------------Toolbar resz---------------------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        searchMenuItem = menu.findItem(R.id.search)
        filterMenuItem = menu.findItem(R.id.filter)
        profileMenuItem = menu.findItem(R.id.profile)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when (itemView) {
            R.id.search -> Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT)
                .show()//ide jonne a Search ablak
            R.id.filter -> Toast.makeText(this, "Filter clicked", Toast.LENGTH_SHORT).show()
            R.id.profile -> {
                getUserInfo()

                //csakis akkor megyek a Profile fragmentbe, amikor megkapom a valaszt (code 200-ast),amikor frissul a code erteke masszoval
                profileViewModel.code.observe(this) {
                    navController.navigate(R.id.profileOwnerFragment)
                }
            }
        }

        return false
    }

    private fun getUserInfo() {
        profileViewModel.user.value!!.username = TokenApplication.username
        profileViewModel.getUserInfo()
    }

    private fun initializeToolBar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    private fun setupToolbar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentLogIn -> hideToolbar()
                R.id.fragmentRegister -> hideToolbar()
                R.id.afterRegisterFragment -> hideToolbar()
                R.id.forgotPasswordFragment -> hideToolbar()
                R.id.afterForgotPasswordFragment -> hideToolbar()
                R.id.timelineFragment -> {
                    showToolbar()
                    //ide probalom majd testreszabni a toolbart,hogy mi jelenjen meg es hogy hogyan
                    supportActionBar!!.title = ""
                    searchMenuItem.isVisible = true
                    filterMenuItem.isVisible = true
                    profileMenuItem.isVisible = true
                    supportActionBar!!.setDisplayUseLogoEnabled(true)
                    supportActionBar!!.setLogo(R.drawable.ic_bazaar_logo_coloured);
                    supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                }
                R.id.profileOwnerFragment, R.id.profileOwnerByOthersFragment -> {
                    showToolbar()
                    supportActionBar!!.title = "Profile"
                    searchMenuItem.isVisible = false
                    filterMenuItem.isVisible = false
                    profileMenuItem.isVisible = true
                    supportActionBar!!.setDisplayUseLogoEnabled(false)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                R.id.productDetailsCustomerFragment -> {
                    showToolbar()
                    supportActionBar!!.title = "Product detail"
                    searchMenuItem.isVisible = false
                    filterMenuItem.isVisible = false
                    profileMenuItem.isVisible = true
                    supportActionBar!!.setDisplayUseLogoEnabled(false)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                R.id.myMarketFragment -> {
                    showToolbar()
                    supportActionBar!!.title = "My market"
                    searchMenuItem.isVisible = true
                    filterMenuItem.isVisible = false
                    profileMenuItem.isVisible = true
                    supportActionBar!!.setDisplayUseLogoEnabled(false)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                R.id.ownerProductDetailsFragment -> {
                    showToolbar()
                    supportActionBar!!.title = "Product detail"
                    searchMenuItem.isVisible = false
                    filterMenuItem.isVisible = false
                    profileMenuItem.isVisible = false
                    supportActionBar!!.setDisplayUseLogoEnabled(false)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                R.id.addProductFragment -> {
                    showToolbar()
                    supportActionBar!!.title = "Create your fare"
                    searchMenuItem.isVisible = false
                    filterMenuItem.isVisible = false
                    profileMenuItem.isVisible = false
                    supportActionBar!!.setDisplayUseLogoEnabled(false)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                else -> showToolbar()
            }
        }
    }

    private fun showToolbar() {
        supportActionBar!!.show()
    }

    private fun hideToolbar() {
        supportActionBar!!.hide()
    }

    //------------------------------Bottom Navigation resz----------------------------
    private fun initializeNavigation() {
        navigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)
    }

    //forras : StackOverFlow
    //ez a fuggveny be fogja allitani, hogy a Bottom Navigacios Menu mely fragmenteken legyen lathato es melyeken nem
    private fun setupNavigationMenuVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentLogIn -> hideBottomNav()
                R.id.fragmentRegister -> hideBottomNav()
                R.id.afterRegisterFragment -> hideBottomNav()
                R.id.forgotPasswordFragment -> hideBottomNav()
                R.id.afterForgotPasswordFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        navigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navigationView.visibility = View.GONE
    }
}