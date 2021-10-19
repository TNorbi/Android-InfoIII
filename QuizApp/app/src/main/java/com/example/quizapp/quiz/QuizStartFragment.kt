package com.example.quizapp.quiz

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.SecondActivity
import com.example.quizapp.TAG_MAIN

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizStartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var userName: EditText
    private lateinit var startButton: Button
    private lateinit var contactButton: Button

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
        val view = inflater.inflate(R.layout.fragment_quiz_start, container, false)

        //hogyha a kapott view nem null akkor fogom inicializalni a dolgokat(valtozokat es Listeners-eket)
        view?.apply {
            initializeViewElements(this) //a megadott view-ban levo valtozokat fogja inicializalni
            registerListeners(this) //a megadott viewban levo Listenereket fogom regisztralni
        }

        /* hogyha nem hasznalom a ?.apply modszert
        if(view != null){
            initializeViewElements(view)
            registerListeners(view)
        }*/

        return view
    }

    private fun initializeViewElements(view: View) {
        userName =
            view.findViewById(R.id.userName) //megadott view-ban levo EditTextet fogja megkeresni ID alapjan
        startButton =
            view.findViewById(R.id.StartButton) //a megadott kurens view-ban levo Buttont fogja megkeresni ID alapjan(ugyszinten xml fajlbol keres)
        contactButton =
            view.findViewById(R.id.contactButton) //a megadott kurens view-ban levo Buttont fogja megkeresni ID alapjan(ugyszinten xml fajlbol keres)
    }

    private fun registerListeners(view: View) {
        startButton.setOnClickListener {
            findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)
        }

        val getContactList = registerForActivityResult(
            ActivityResultContracts.PickContact(),
            ActivityResultCallback {
                //le kell kezelni a Uri-t!

                val lista = listOf(ContactsContract.Contacts.DISPLAY_NAME).toTypedArray()

                val cursor = requireActivity().contentResolver.query(it, lista, null, null, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        this.userName.setText(cursor.getString(0))
                    }
                    cursor.close()
                }
            })

        contactButton.setOnClickListener {
            //itt kene megnyitmi a kontakt listamat
            getContactList.launch(null)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizStartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizStartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}