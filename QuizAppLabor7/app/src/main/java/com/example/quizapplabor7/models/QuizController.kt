package com.example.quizapplabor7.models


import android.content.res.AssetManager
import java.util.*

class QuizController(file: AssetManager) {
    val questions = arrayListOf<Question>()

    init { //ez a diagram szerint a QuizController()-nek fog megfelelni

        val inputStream = file.open("questions.txt")

        val lines = inputStream.bufferedReader().use { it.readLines() }

        var kerdes: String
        var helyesValasz: String

        for (i in 0 until lines.size) {

            val segedLista = lines[i].split('|')

            kerdes = segedLista[0]
            helyesValasz = segedLista[2]
            val answers = segedLista[1].split(';')
            val question = Question(answers, kerdes, helyesValasz)
            questions.add(question)


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