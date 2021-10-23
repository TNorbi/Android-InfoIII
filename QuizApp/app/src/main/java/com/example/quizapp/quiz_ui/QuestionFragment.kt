package com.example.quizapp.quiz_ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.QuizViewModel
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: QuizViewModel by activityViewModels()
    private lateinit var questionTitle: TextView
    private lateinit var radioGroup : RadioGroup
    private lateinit var nextButton: Button

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
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        //itt kezelem le a Back buttont-----------------------------------------
        //egy Alert Dialog fog megjeleni mindig,amikor a User a Back Buttont megnyomja
        //ha a User a No opciot valassza,akkor a kurens kerdesnel marad es folytathatja kitolteni a Quizt
        //Ha a User a Yes opciot valassz, akkor befejezi a Quizt, egyenesen a QuizEndFragmentbe fog ugrani a program,ahol a User ki lesz ertekelve
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle("Exit")
                alertDialog.setMessage("Are you sure you want to end this quiz?")

                alertDialog.setPositiveButton("Yes"){ _, _ ->
                    findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
                }

                alertDialog.setNegativeButton("No") { _, _ ->
                }


                alertDialog.show()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)
        //---------------------------------------------------------------------------------------------------

        view?.apply {
            initialize(this)
            showQuestionAnswers()
            initializeListeners(this)
        }
        return view
    }

    private fun initialize(view: View) {
        //itt be kell olvassam a kerdest a fajlbol
        questionTitle = view.findViewById(R.id.questionTitle)
        nextButton = view.findViewById(R.id.nextButton)
        radioGroup = view.findViewById(R.id.RadioGroup)

        //itt be kell olvassam az answers tombbe a valaszokat
        //4 db valaszt kell megjeleniteni a kepernyon, illetve a hozzajuk egy-egy Radio buttont!
    }

    private fun showQuestionAnswers(){

        val currentQuestionID = viewModel.getCurrentQuestionID()
        var radioButton : RadioButton

        if(currentQuestionID == viewModel.getController().questions.size-1){
            nextButton.text = "Submit"
        }

        questionTitle.text = viewModel.getController().questions[currentQuestionID].text

        val answers = viewModel.getController().questions[currentQuestionID].answers.shuffled()

        /*radioGroup.findViewById<RadioButton>(R.id.radioButton1).text = answers[0]
        radioGroup.findViewById<RadioButton>(R.id.radioButton2).text = answers[1]
        radioGroup.findViewById<RadioButton>(R.id.radioButton3).text = answers[2]
        radioGroup.findViewById<RadioButton>(R.id.radioButton4).text = answers[3]*/

        for(i in 0 until answers.size){
            radioButton = RadioButton(context)
            radioButton.text = answers[i]
            radioButton.id = i
            radioButton.setTextColor(Color.parseColor("black"))
            radioGroup.addView(radioButton)
        }
    }


    private fun initializeListeners(view: View) {
        nextButton.setOnClickListener {
            //itt meg kell nezni, hogy hanyadik kerdesnel vagyunk
            //ha meg van kerdes, akkor meghivja onmagat a fragment a kovetkezo kerdes-valasz sorozattal
            //ha utolso kerdesnel vagyunk akkor atmegyunk a 3-dik es egyben utolso fragmentbe, a fragment quiz endbe!
            //itt kell majd hasznaljak ViewModelt!

            viewModel.increaseQuestionID()

            if (viewModel.getCurrentQuestionID() < viewModel.getController().questions.size) {
                findNavController().navigate(R.id.action_questionFragment_self)
            } else {
                findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}