package com.example.quizapplabor6


import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.quizapp.R
import com.example.quizapplabor6.models.SharedViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


const val TAG_MAIN: String = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var assetManager: AssetManager
    private val viewModel: SharedViewModel by viewModels()
    //itt az onCreate fog eloszor meghivodni es majd csak az onStart

    override fun onStart() {
        super.onStart()
        Log.i(TAG_MAIN, "onStart() called")
    }

    //onCreate = ez 1 activity,ez 1 lifecycle (eletcilkus metodus) ,ez kotelezo modon itt kell legyen ahhoz hogy lassunk is valamit
    //ezeknek a lifecycle-nek ertelme egyreszt, hogy bizonyos adatokat le lehessen menteni abban az esetben ha valami hiba lep fel

    override fun onResume() {
        super.onResume()
        Log.i(TAG_MAIN, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_MAIN, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_MAIN, "onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG_MAIN, "onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_MAIN, "onDestroy() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_MAIN, "onCreate() called")
        setContentView(R.layout.activity_main)



        initializeView()
        initializeMenu()
    }

    private fun initializeView() {
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        assetManager = this.assets //egy AssetManagert hozok letre,amit majd a fajl megnyitasara es annak tartalmara valo feldolgozasnal fogok felhasznalni a QuizController-ben!
        viewModel.initializeQuizController(assetManager)
    }

    private fun initializeMenu() {
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true

            when(menuItem.itemId){
                R.id.navigation_menu_home -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                R.id.navigation_menu_quizTime -> findNavController(R.id.nav_host_fragment).navigate(
                    R.id.quizStartFragment
                )
                R.id.navigation_menu_profile -> findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                R.id.navigation_menu_ListOfQuestions -> findNavController(R.id.nav_host_fragment).navigate(
                    R.id.questionListFragment
                )
                R.id.navigation_menu_newQuestion -> findNavController(R.id.nav_host_fragment).navigate(
                    R.id.questionAddFragment
                )
            }

            drawerLayout.close()
            true
        }
    }
}
