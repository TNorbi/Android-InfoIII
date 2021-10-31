package com.example.quizapplabor6.ui.questions

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.Question
import com.example.quizapplabor6.models.SharedViewModel
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var questionTitle: TextInputEditText
    private lateinit var addAnswerButton: Button
    private lateinit var answerGroup: RadioGroup
    private lateinit var addQuestionButton: Button
    private val viewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_add, container, false)

        view?.apply {
            initializeView(this)
            initializeListeners()
        }

        return view
    }


    private fun initializeView(view: View) {
        questionTitle = view.findViewById(R.id.newQuestionTitle)
        addAnswerButton = view.findViewById(R.id.addAnswer)
        answerGroup = view.findViewById(R.id.newQuestionRadioGroup)
        addQuestionButton = view.findViewById(R.id.addQuestionButton)
    }


    private fun initializeListeners() {
        addAnswerButton.setOnClickListener {
            //ha a User megnyomja az ADD Answer gombot,akkor uj valasz fog megadni a kerdesre (dinamikusan adok hozza minden uj kerdeshez valaszt)
            val radioButton = RadioButton(context)
            radioButton.text = ""
            radioButton.id = answerGroup.size
            radioButton.setButtonDrawable(android.R.drawable.btn_radio)
            radioButton.setTextColor(Color.parseColor("black"))
            answerGroup.addView(radioButton)
        }

        addQuestionButton.setOnClickListener {
            //Ha a User ranyom az ADD Question gombra,akkor az uj kerdest beteszem a viewModelben levo quizcontrollerbe
            //egy Toast uzenettel fogom ertesiteni a Usert, hogy az uj kerdest sikeresen be lett teve
            // vegezetul pedig megint meghivom a fragmentet (onmagat)

            addNewQuestion()
            Toast.makeText(context,"Question was added!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_questionAddFragment_self)
        }
    }

    private fun addNewQuestion() {
        val title = questionTitle.text.toString()
        val answers = ArrayList<String>()
        val rightAnswer = ""

        val question = Question(answers,title,rightAnswer)

        viewModel.getController().questions.add(question)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}