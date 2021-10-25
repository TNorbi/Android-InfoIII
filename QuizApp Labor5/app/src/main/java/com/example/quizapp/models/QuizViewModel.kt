package com.example.quizapp.models

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){
    private lateinit var quizController: QuizController
    private var points = 0
    private var currentQuestionID = 0

    fun initializeQuizController(file: AssetManager){
        quizController = QuizController(file)
    }

    fun getController() = quizController

    fun increasePoints(){
        points++
    }

    fun getPoints() = points

    fun setPoints(value: Int){
        points = value
    }

    fun increaseQuestionID(){
        currentQuestionID++
    }

    fun setCurrentQuestionID(value : Int){
        currentQuestionID = value
    }

    fun getCurrentQuestionID() = currentQuestionID
}