package com.example.quizapplabor7.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.quizapplabor7.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var testSkillButton: Button
    private lateinit var readQuestionsButton: Button
    private lateinit var createQuestionsButton: Button

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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view?.apply {
            //itt fogom inicializalni a Button valtozokat,illetve az onClickListenereket
            initializeView(this)
            initializeListeners(this)
        }

        return view
    }

    private fun initializeView(view: View) {
        testSkillButton = view.findViewById(R.id.testSkillsButton)
        readQuestionsButton = view.findViewById(R.id.readQuestionsButton)
        createQuestionsButton = view.findViewById(R.id.createQuestionsButton)
    }

    private fun initializeListeners(view: View) {
        testSkillButton.setOnClickListener {
            //ha a User ranyom a test your skills gombra akkor atugrok a quiz start fragmentbe
            findNavController().navigate(R.id.quizStartFragment)
        }

        readQuestionsButton.setOnClickListener {
            //ha a User ranyom a read Questions gombra akkor atugrok a Question List Fragmentbe
            findNavController().navigate(R.id.questionListFragment)
        }

        createQuestionsButton.setOnClickListener {
            //ha a User ranyom a Create Questions gombra akkor atugrok a QuestionAddFragmentbe
            findNavController().navigate(R.id.questionAddFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}