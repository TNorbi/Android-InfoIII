package com.example.quizapplabor6.ui.quiz

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.QuizViewModel
import androidx.databinding.DataBindingUtil
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.models.QuizController


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
    private lateinit var radioGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var currentQuestionID : Number
    private lateinit var quizController : QuizController

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

        //val view = inflater.inflate(R.layout.fragment_question, container, false)

        /**-----------------Itt elkezdtem hasznalni a Data Bindingot---------------------**/

        val binding: FragmentQuestionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_question, container, false
        ) //itt letrehoztam a data bindingot

        val view = binding.root //a letrejott data binding-tol lekerdeztem a view-jat


        resolveBackButton() //ez a fuggveny lekezeli a Back Button problemat

        /**Ez a data binding nelkuli verzio**/
        /*view?.apply {
            initialize(this)
            showQuestionAnswers()
            initializeListeners(this)
        }
        return view*/

        /**Ez pedig a data binding verzio**/

        initialize(binding)
        initializeListeners(view)
        showQuestionAnswers()

        return view
    }

    private fun resolveBackButton() {
        //egy Alert Dialog fog megjeleni mindig,amikor a User a Back Buttont megnyomja
        //ha a User a No opciot valassza,akkor a kurens kerdesnel marad es folytathatja kitolteni a Quizt
        //Ha a User a Yes opciot valassz, akkor befejezi a Quizt, egyenesen a QuizEndFragmentbe fog ugrani a program,ahol a User ki lesz ertekelve

        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle("Exit")
                alertDialog.setMessage("Are you sure you want to end this quiz?")

                alertDialog.setPositiveButton("Yes") { _, _ ->
                    findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
                }

                alertDialog.setNegativeButton("No") { _, _ ->
                }


                alertDialog.show()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    private fun initialize(binding: FragmentQuestionBinding) {

        /**--------Data Binding nelkuli verzio(parametereben view: View volt)----------------**/
        /*questionTitle = view.findViewById(R.id.questionTitle)
        nextButton = view.findViewById(R.id.nextButton)
        radioGroup = view.findViewById(R.id.RadioGroup)*/

        /**-------------Data Binding-os verzio-----------------**/
        questionTitle = binding.questionTitle
        nextButton = binding.nextButton
        radioGroup = binding.RadioGroup
        currentQuestionID = viewModel.getCurrentQuestionID()
        quizController = viewModel.getController()
    }

    private fun showQuestionAnswers() {

        //val currentQuestionID = viewModel.getCurrentQuestionID()
        //val quizController = viewModel.getController()
        var radioButton: RadioButton

        if (currentQuestionID == quizController.questions.size - 1) {
            nextButton.text = "Submit"
        }

        questionTitle.text = quizController.questions[currentQuestionID as Int].text

        val answers = quizController.questions[currentQuestionID as Int].answers.shuffled()

        /*radioGroup.findViewById<RadioButton>(R.id.radioButton1).text = answers[0]
        radioGroup.findViewById<RadioButton>(R.id.radioButton2).text = answers[1]
        radioGroup.findViewById<RadioButton>(R.id.radioButton3).text = answers[2]
        radioGroup.findViewById<RadioButton>(R.id.radioButton4).text = answers[3]*/

        //ez a kodreszlet dinamikusan fog RadioButton-eket csatolni a RadioGrouphoz
        //annyi RadioButton lesz ahany darab valaszunk van
        for (i in 0 until answers.size) {
            radioButton = RadioButton(context)
            radioButton.text = answers[i]
            radioButton.id = i
            radioButton.setButtonDrawable(android.R.drawable.btn_radio)
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

            //meg kell nezzem, hogy a User a helyes valaszt valasztotta vagy sem, az szerint fog kapni pontot
            if(isGoodAnswer()){
                viewModel.increasePoints()
            }

            viewModel.increaseQuestionID()

            if (currentQuestionID != quizController.questions.size-1) {
                findNavController().navigate(R.id.action_questionFragment_self)
            } else {
                findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
            }
        }
    }

    private fun isGoodAnswer(): Boolean {

        val index = radioGroup.checkedRadioButtonId

        if(index < 0){
            //a felhasznalo nem valasztott ki egy opciot se => false-t teritek vissza => nem fog kapni majd pontot
            return false
        }

        val valasztottOpcio = radioGroup.findViewById<RadioButton>(index)

        if(valasztottOpcio.text == quizController.questions[currentQuestionID as Int].goodAnswer){
            return true
        }

        return false
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