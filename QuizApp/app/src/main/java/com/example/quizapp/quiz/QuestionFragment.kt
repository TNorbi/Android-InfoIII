package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var questionTitle : TextView
private lateinit var answers : ArrayList<String>
private lateinit var nextButton : Button

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view =  inflater.inflate(R.layout.fragment_question, container, false)

        view?.apply {
            initialize(this)
            initializeListeners(this)
        }

        return view
    }

    private fun initialize(view : View){
        //itt be kell olvassam a kerdest a fajlbol
        questionTitle = view.findViewById(R.id.questionTitle)
        nextButton = view.findViewById(R.id.nextButton)
        //itt be kell olvassam az answers tombbe a valaszokat
        //4 db valaszt kell megjeleniteni a kepernyon, illetve a hozzajuk egy-egy Radio buttont!
    }

    private fun initializeListeners(view: View){
        nextButton.setOnClickListener {
            //itt meg kell nezni, hogy hanyadik kerdesnel vagyunk
            //ha meg van kerdes, akkor meghivja onmagat a fragment a kovetkezo kerdes-valasz sorozattal
            //ha utolso kerdesnel vagyunk akkor atmegyunk a 3-dik es egyben utolso fragmentbe, a fragment quiz endbe!
            //itt kell majd hasznaljak ViewModelt!

            if(kurensKerdes < osszKerdesek){
                findNavController().navigate(R.id.action_questionFragment_self)
            }
            else{
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