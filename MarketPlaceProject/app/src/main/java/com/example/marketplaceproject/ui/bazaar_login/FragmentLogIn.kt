package com.example.marketplaceproject.ui.bazaar_login

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.marketplaceproject.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogIn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var title : TextView
    private lateinit var email_input : EditText
    private lateinit var password_input: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

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
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        view?.apply {
            initializeView(this)
            changeColorOfTitle()
            initializeListeners(this)
        }

        return view
    }

    private fun initializeListeners(view: View) {
        loginButton.setOnClickListener{
            //itt meg kell hivjam majd a viewModelben a login Post kereset(coroutine hivas)
        }

        registerButton.setOnClickListener {
            //ha a User ranyom a Sign Up gombra,akkor atnavigalja a Register reszre
            findNavController().navigate(R.id.action_fragmentLogIn_to_fragmentRegister)
        }
    }

    private fun initializeView(view: View) {
        title = view.findViewById(R.id.logInTitle)
        email_input = view.findViewById(R.id.emailInput)
        password_input = view.findViewById(R.id.passwordInput)
        loginButton  = view.findViewById(R.id.logInButton)
        registerButton = view.findViewById(R.id.login_registerButton)
    }

    private fun changeColorOfTitle() {
        //ez a kicsi kod megnezi, hogy a telefonunk night modban van vagy sem
        //ha night modban van akkor a text color feher lesz (hogy konyebben lehessen latni), ellenkezo esetben fekete szin marad (forras : Stack)
        val nightModeFlags = requireContext().resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> title.setTextColor(Color.parseColor("white"))
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> title.setTextColor(
                Color.parseColor("black"))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLogIn.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLogIn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}