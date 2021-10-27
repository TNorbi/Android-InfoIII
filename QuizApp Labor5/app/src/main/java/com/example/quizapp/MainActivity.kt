package com.example.quizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.quizapp.models.QuizViewModel


const val TAG_MAIN: String = "MainActivity"


class MainActivity : AppCompatActivity() {

    //itt az onCreate fog eloszor meghivodni es majd csak az onStart

    override fun onStart() {
        super.onStart()
        Log.i(TAG_MAIN,"onStart() called")
    }

    //onCreate = ez 1 activity,ez 1 lifecycle (eletcilkus metodus) ,ez kotelezo modon itt kell legyen ahhoz hogy lassunk is valamit
    //ezeknek a lifecycle-nek ertelme egyreszt, hogy bizonyos adatokat le lehessen menteni abban az esetben ha valami hiba lep fel

    override fun onResume() {
        super.onResume()
        Log.i(TAG_MAIN,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_MAIN,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_MAIN,"onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG_MAIN,"onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_MAIN,"onDestroy() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_MAIN,"onCreate() called")
        setContentView(R.layout.activity_main)

        val viewModel : QuizViewModel by viewModels()

    }
}
