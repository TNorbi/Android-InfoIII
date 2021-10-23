package com.example.quizapp.models


import android.content.res.AssetManager
import java.util.*

class QuizController(file: AssetManager) {
     val questions = arrayListOf<Question>()

    init { //ez a diagram szerint a QuizController()-nek fog megfelelni

        val inputStream = file.open("questions.txt")

        val lines = inputStream.bufferedReader().use { it.readLines() }

        for (i in 0 until lines.size) {
            if (i % 5 == 0) {
                //kerdesrol van szo
                //uj valaszlista
                val answers = ArrayList<String>()
                for (j in i + 1..i + 4) {
                    answers.add(lines[j])
                }

                val question = Question(answers, lines[i])
                questions.add(question)
            }
        }
        inputStream.close()
    }

    fun randomizeQuestions() = questions.shuffle()

    fun doQuiz(numberOfQuestions: Int) {
        randomizeQuestions() //keveri a kerdeseket
        var countCorrect = 0
        val cin = Scanner(System.`in`)
        for (i in 0 until numberOfQuestions) {
            println(questions[i].text)
            var betu = 'A'
            questions[i].answers.shuffled().forEach { println("$betu.$it"); betu++ }
            print(">>")
            val valasz = cin.next()
            if (valasz == "A") {
                countCorrect++
            }
        }

        println("Result: ${countCorrect / numberOfQuestions}")
    }
}

//gradle -> build tool, buildelesben segit