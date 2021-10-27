package com.example.quizapplabor6.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.QuizViewModel
import java.time.Duration

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [QuizEndFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizEndFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var quizEndTitle: TextView
    private lateinit var quizResult: TextView
    private lateinit var tryAgainButton: Button
    private val viewModel : QuizViewModel by activityViewModels()

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
        val view = inflater.inflate(R.layout.fragment_quiz_end, container, false)

        resolveBackButton()

        view?.apply {
            initialize(this)
            initializeListeners(this)
        }


        quizResult.text = "${viewModel.getPoints()}/${viewModel.getController().questions.size}"

        return view
    }


    private fun resolveBackButton(){
        //itt lekezelem a Back Buttont, hogy ne lepjen vissza az utolso kerdesre, ugymond elhitettem a userrel, hogy a back buttont nem lehet hasznalni a kiertekeleskor
        val callBack = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                return
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callBack)
    }

    private fun initialize(view: View) {
        quizEndTitle = view.findViewById(R.id.quizEndTitle)
        quizResult = view.findViewById(R.id.quizResult)
        tryAgainButton = view.findViewById(R.id.tryAgainButton)
    }

    private fun initializeListeners(view: View) {
        tryAgainButton.setOnClickListener{
            //ha a User megnyomja a Try Again gombot akkor visszaugrunk a program kezdokepernyojere, vagyis vissza kell menjunk a Quiz Start Fragmentbe
            viewModel.setPoints(0)
            viewModel.setCurrentQuestionID(0)
            findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizEndFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizEndFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}