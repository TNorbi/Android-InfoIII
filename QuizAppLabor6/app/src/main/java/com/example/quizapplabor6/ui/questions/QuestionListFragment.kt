package com.example.quizapplabor6.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.Question
import com.example.quizapplabor6.adapter.QuizAdapter
import com.example.quizapplabor6.models.MyItem
import com.example.quizapplabor6.models.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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

        val view = inflater.inflate(R.layout.fragment_question_list, container, false)

        view?.apply {
            initializeRecyclerView(this)
        }

        return view
    }

    private fun initializeRecyclerView(view: View) {
        val list = uploadList(viewModel.getController().questions.size)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = QuizAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    //ez a fuggveny fel fogja tolteni egy listaban a kerdeseket es a hozza tartozo helyes valaszokat
    private fun uploadList(size: Int): List<MyItem> {

        val list = ArrayList<MyItem>()
        val quizController = viewModel.getController()
        var currentQuestion: Question
        var currentTitle: String
        var currentAnswer: String

        for (i in 0 until size) {
            currentQuestion = quizController.questions[i]
            if (currentQuestion.text.length > 20) {
                currentTitle = currentQuestion.text.substring(0..20) + "..."
            } else {
                currentTitle = currentQuestion.text
            }

            if (currentQuestion.goodAnswer.length > 10) {
                currentAnswer = currentQuestion.goodAnswer.substring(0..10) + "..."
            } else {
                currentAnswer = currentQuestion.goodAnswer
            }

            val item = MyItem(currentTitle, currentAnswer)

            list += item
        }
        //list += MyItem("asd","asd")
        return list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}