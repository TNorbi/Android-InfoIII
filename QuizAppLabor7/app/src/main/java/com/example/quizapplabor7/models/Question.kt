package com.example.quizapplabor7.models

//ez konstruktorkent fog viselkedni es ami parameterben van az automatikusan annak tagja is lesz(quiz.Question osztalynak ezzel megadom a tagjait es azoknak ertekeit,default konstruktorkent fog viselkedni)
data class Question(val answers: List<String>, val text: String, val goodAnswer: String)