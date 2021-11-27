package com.example.marketplaceproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeToolBar()
        initializeNavigation()
        setupNavigationMenuVisibility()
    }

    //-----------------Toolbar resz---------------------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when(itemView){
            R.id.search -> Toast.makeText(this,"Search clicked",Toast.LENGTH_SHORT).show()//ide jonne a Search ablak
            R.id.filter ->Toast.makeText(this,"Filter clicked",Toast.LENGTH_SHORT).show()
            R.id.profile ->Toast.makeText(this,"Profile clicked",Toast.LENGTH_SHORT).show()
        }

        return false
    }

    private fun initializeToolBar(){
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    //------------------------------Bottom Navigation resz----------------------------
    private fun initializeNavigation(){
        navigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
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