package com.example.quizapplabor7.ui.questions

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.quizapplabor7.R

import com.example.quizapplabor7.models.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var questionTitle: TextView
    private lateinit var answersLayout: LinearLayout
    private val viewModel: SharedViewModel by activityViewModels()

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
        val view = inflater.inflate(R.layout.fragment_question_detail, container, false)

        view?.apply {
            initializeView(this)
            displayQuestion()
        }

        return view
    }

    private fun initializeView(view: View) {
        questionTitle = view.findViewById(R.id.questionDetail_title)
        answersLayout = view.findViewById(R.id.questionDetail_answersLayout)
    }

    private fun displayQuestion() {
        val position = viewModel.adapterCurrentPosition

        questionTitle.text = viewModel.getController().questions[position].text

        val answers = viewModel.getController().questions[position].answers
        val goodAnswer = viewModel.getController().questions[position].goodAnswer

        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(20, 20, 20, 40)

        for(currentAnswer in answers){
            val textView = TextView(context)
            textView.text = currentAnswer
            textView.layoutParams = params

            if(currentAnswer == goodAnswer){
                textView.setTextColor(Color.parseColor("teal"))
                textView.setTypeface(null, Typeface.BOLD);
            }
            else{
                textView.setTextColor(Color.parseColor("black"))
            }

            answersLayout.addView(textView)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}