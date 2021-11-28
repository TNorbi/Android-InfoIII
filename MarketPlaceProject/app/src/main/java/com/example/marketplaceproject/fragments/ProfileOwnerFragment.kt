package com.example.marketplaceproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.marketplaceproject.R
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceproject.MainActivity
import com.example.marketplaceproject.repository.Repository
import com.example.marketplaceproject.viewModels.profile.ProfileViewModel
import com.example.marketplaceproject.viewModels.profile.ProfileViewModelFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileOwnerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileOwnerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var userPicture: ImageView
    private lateinit var username: TextView
    private lateinit var userEmail: EditText
    private lateinit var editUserName: EditText
    private lateinit var userPhoneNumber: EditText
    private lateinit var publishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //lekerem a profileViewModelt,amit a MainActivityben hoztam letre
        val factory = ProfileViewModelFactory(Repository())
        profileViewModel = ViewModelProvider(requireActivity(),factory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_owner, container, false)

        view?.apply {
            initializeView(this)
            navigationBackInitialize()
            listUserDatas()
            initializeListeners(this)
        }

        return view
    }

    private fun listUserDatas() {
        username.text = profileViewModel.user.value!!.username
        userEmail.setText(profileViewModel.user.value!!.email)
        userPhoneNumber.setText(profileViewModel.user.value!!.phone_number)
    }

    private fun navigationBackInitialize() {
        //visszafele navigalo gomb funkcionalitasa
        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

        toolbar.setNavigationOnClickListener {
            actionBar!!.setDisplayHomeAsUpEnabled(false) //ez kikapcsolja majd a visszafele gombot a toolbarban
            actionBar.title = ""
            val searchItem = (activity as MainActivity).getSearchMenuItem()
            val filterMenuItem = (activity as MainActivity).getFilterMenuItem()
            searchItem.isVisible = true
            filterMenuItem.isVisible = true
            activity!!.onBackPressed()
        }
    }

    private fun initializeListeners(view: View) {
        publishButton.setOnClickListener {
            //itt le kell majd implementaljam az Update user Data-t(Postman) funkcionalitast!
        }
    }

    private fun initializeView(view: View) {
        userPicture = view.findViewById(R.id.userPicture)
        username = view.findViewById(R.id.userName)
        userEmail = view.findViewById(R.id.userEmail)
        editUserName = view.findViewById(R.id.edit_username)
        userPhoneNumber = view.findViewById(R.id.user_phone_number)
        publishButton = view.findViewById(R.id.publishButton)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileOwnerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileOwnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}