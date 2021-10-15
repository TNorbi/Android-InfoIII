package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var userName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)




        initializer()
    }

    private fun initializer() {
        //ezzel megszerzem azt az intet, amelyik elinditotta ezt az activityt es kiszedi belole a stringet
        val uzenet = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        userName = findViewById<TextView>(R.id.SecondActivity_UserName).apply {
            text = uzenet
        }
    }
}