package quiz

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class QuizController {
    val questions = arrayListOf<Question>()

    init { //ez a diagram szerint a QuizController()-nek fog megfelelni
            //Kotlinban az init blokk lesz tulajdonkeppen a konstruktor
        val lines = File("questions.txt").readLines()


        for(i in 0 until lines.size){
            if(i%5==0){
                //kerdesrol van szo
                //uj valaszlista
                val answers = ArrayList<String>()
                for(j in i+1..i+4) {
                    answers.add(lines[j])
                }

                val question = Question(answers,lines[i])
                questions.add(question)
            }
        }
    }

    fun randomizeQuestions(){
        questions.shuffle()
    }

    fun doQuiz(numberOfQuestions: Int){
        randomizeQuestions() //keveri a kerdeseket
        var countCorrect = 0
        val cin = Scanner(System.`in`)
        for(i in 0 until numberOfQuestions){
            println(questions[i].text)
            var betu = 'A'
            questions[i].answers.shuffled().forEach{ println("$betu.$it"); betu++}
            print(">>")
            val valasz= cin.next()
            if(valasz == "A"){
                countCorrect++
            }
        }

        println("Result: ${countCorrect/numberOfQuestions}")
    }
}

//gradle -> build tool, buildelesben segit