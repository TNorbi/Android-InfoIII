package com.example.quizapplabor6.ui.questions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
class QuestionListFragment : Fragment(), QuizAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var list: ArrayList<MyItem>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizAdapter

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
        list = uploadList(viewModel.getController().questions.size)
        adapter = QuizAdapter(list, this)

        //itt fogom inicializalni a RecyclerView-ot
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    //ez a fuggveny fel fogja tolteni egy listaban a kerdeseket es a hozza tartozo helyes valaszokat
    private fun uploadList(size: Int): ArrayList<MyItem> {

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

        return list
    }

    override fun onDetailsClick(position: Int) {
        viewModel.adapterCurrentPosition = position
        findNavController().navigate(R.id.action_questionListFragment_to_questionDetailFragment)
    }

    override fun onDeleteClick(position: Int) {
        list.removeAt(position) //kitorlom a RecyclerView listajabol a kivalasztott kerdest

        //majd kitorlom a kerdest a questions listabol
        if (!(position < 0 || position >= viewModel.getController().questions.size)) {
            viewModel.getController().questions.removeAt(position)
            adapter.notifyItemRemoved(position)

        }
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