package com.example.quizapplabor7.models

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import com.example.quizapplabor7.models.Question
import com.example.quizapplabor7.models.QuizController

class SharedViewModel : ViewModel() {
    private lateinit var quizController: QuizController
    private var currentpoints = 0
    private var currentQuestionID = 0
    private var playerName = ""
    private var highScore = 0
    var adapterCurrentPosition = 0

    fun updateHighScore(): Boolean {
        if (highScore < currentpoints) {
            highScore = currentpoints
            return true
        }

        return false
    }

    fun initializeQuizController(file: AssetManager) {
        quizController = QuizController(file)
    }

    fun setPlayerName(name: String) {
        playerName = name
    }

    fun getPlayerName() = playerName

    fun getController() = quizController

    fun increasePoints() {
        currentpoints++
    }

    fun getPoints() = currentpoints

    fun setPoints(value: Int) {
        currentpoints = value
    }

    fun increaseQuestionID() {
        currentQuestionID++
    }

    fun setCurrentQuestionID(value: Int) {
        currentQuestionID = value
    }

    fun getCurrentQuestionID() = currentQuestionID

    fun getHighScore() = highScore

    fun addQuestion(question: Question){
        quizController.questions.add(question)
    }
}