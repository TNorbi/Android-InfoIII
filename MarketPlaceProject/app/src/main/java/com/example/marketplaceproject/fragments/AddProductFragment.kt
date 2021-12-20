package com.example.marketplaceproject.fragments

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplaceproject.R
import com.example.marketplaceproject.repository.Repository
import com.example.marketplaceproject.viewModels.addproduct.AddProductViewModel
import com.example.marketplaceproject.viewModels.addproduct.AddProductViewModelFactory
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModel
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var addProductViewModel: AddProductViewModel
    private lateinit var listViewModel: TimelineViewModel
    private lateinit var availabilitySwitch: Switch
    private lateinit var uploadDate: TextView
    private lateinit var detailsFareLabel: TextView
    private lateinit var titleInput: TextInputEditText
    private lateinit var priceAmountInput: TextInputEditText
    private lateinit var priceTypeSpinner: Spinner
    private lateinit var availableAmountInput: TextInputEditText
    private lateinit var availableAmountSpinner: Spinner
    private lateinit var productDescription: TextInputEditText
    private lateinit var titleInputLayout: TextInputLayout
    private lateinit var priceAmountLayout: TextInputLayout
    private lateinit var availableAmountLayout: TextInputLayout
    private lateinit var descriptionLayout: TextInputLayout
    private lateinit var launchFare: Button
    private lateinit var priceType: String
    private lateinit var amountType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //itt letrehozom az addproduct viewModeljet
        val factory = AddProductViewModelFactory(requireContext(), Repository())
        addProductViewModel =
            ViewModelProvider(requireActivity(), factory).get(AddProductViewModel::class.java)

        val factory2 = TimelineViewModelFactory(Repository())
        listViewModel =
            ViewModelProvider(requireActivity(), factory2).get(TimelineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        view?.apply {
            initializeView(this)
            initializeSpinners()
            changeColorOfViewElements()
            initializeListeners(this)
        }

        addProductViewModel.productID.observe(viewLifecycleOwner) {
            //hogyha a productId modosult,akkor azt jelenti, hogy sikeresen hozzaadodott a termekunk az adatbazisra
            //ebben az esetben visszaterunk a My Market ablakra

            if (addProductViewModel.modosultProductID) {
                addProductViewModel.modosultProductID = false

                Toast.makeText(
                    context,
                    "Your product has been successfully added!",
                    Toast.LENGTH_LONG
                )
                    .show()

                listViewModel.getProducts()

                findNavController().navigate(R.id.action_addProductFragment_to_myMarketFragment)
            }
        }

        return view
    }

    private fun initializeSpinners() {
        //------------------------price type spinner inicializalasa----------------------

        val priceTypes = resources.getStringArray(R.array.Price_Type)

        val adapterPrice =
            ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, priceTypes)
        priceTypeSpinner.adapter = adapterPrice

        priceTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                priceType = priceTypes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        //---------------------------available amount spinner inicializalasa--------------
        val amount_type = resources.getStringArray(R.array.Amount_type)
        val adapterAmountType =
            ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, amount_type)
        availableAmountSpinner.adapter = adapterAmountType

        availableAmountSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                amountType = amount_type[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initializeView(view: View) {
        availabilitySwitch = view.findViewById(R.id.availability_switch)
        uploadDate = view.findViewById(R.id.avability_date_label)
        detailsFareLabel = view.findViewById(R.id.details_fare_label)
        titleInput = view.findViewById(R.id.title_input_edit)

        priceAmountInput = view.findViewById(R.id.price_amount_input)
        priceAmountInput.inputType = InputType.TYPE_CLASS_NUMBER
        priceTypeSpinner = view.findViewById(R.id.price_type_spinner)

        availableAmountInput = view.findViewById(R.id.available_amount_textinput)
        availableAmountInput.inputType = InputType.TYPE_CLASS_NUMBER
        availableAmountSpinner = view.findViewById(R.id.available_amount_spinner)

        productDescription = view.findViewById(R.id.product_description_input)

        titleInputLayout = view.findViewById(R.id.title_input_layout)
        priceAmountLayout = view.findViewById(R.id.price_amount_textinput_layout)
        availableAmountLayout = view.findViewById(R.id.available_amount_textinput_layout)
        descriptionLayout = view.findViewById(R.id.product_description_input_layout)

        launchFare = view.findViewById(R.id.launch_fair_button)
    }

    private fun initializeListeners(view: View) {
        availabilitySwitch.setOnClickListener {
            if (availabilitySwitch.isChecked) {
                availabilitySwitch.text = "Active"
                availabilitySwitch.setTextColor(Color.parseColor("#00B5C0"))
            } else {
                availabilitySwitch.text = "Inactive"
                availabilitySwitch.setTextColor(Color.parseColor("#9A9A9A"))
            }
        }

        launchFare.setOnClickListener {
            //hogyha a User megnyomja a Launch Fare gombot, akkor feltolti az uj termeket az adatbazisba
            if (titleInput.text!!.isEmpty() || productDescription.text!!.isEmpty() || priceAmountInput.text!!.isEmpty() || availableAmountInput.text!!.isEmpty()) {
                Toast.makeText(
                    context,
                    "Title, description , price or quantity missing",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addProductViewModel.newProduct.value.let {
                    //itt feltoltom a viewModelt a user altal megadott adatokkal,majd elinditom az Add Product kerest/muveletet
                    Log.d("xxx", "Title: ${titleInput.text}")
                    it!!.title = titleInput.text.toString()
                    it!!.description = productDescription.text.toString()
                    it!!.price_per_unit = priceAmountInput.text.toString()
                    it!!.units = availableAmountInput.text.toString()
                    it!!.is_active = availabilitySwitch.isChecked
                    it!!.amount_type = amountType
                    it!!.price_type = priceType
                }

                addProductViewModel.addProduct()
            }
        }
    }

    //forras: StackOverFlow
    private fun changeColorOfViewElements() {
        //ez a kicsi kod megnezi, hogy a telefonunk night modban van vagy sem
        //ha night modban van akkor a text color feher lesz (hogy konyebben lehessen latni), ellenkezo esetben fekete szin marad (forras : Stack)
        val nightModeFlags = requireContext().resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                detailsFareLabel.setTextColor(Color.parseColor("white"))
                titleInput.setTextColor(Color.parseColor("white"))
                titleInputLayout.boxStrokeColor = Color.parseColor("white")
                titleInputLayout.boxBackgroundColor = Color.parseColor("#3A3B3C")
                priceAmountInput.setTextColor(Color.parseColor("white"))
                priceAmountLayout.boxBackgroundColor = Color.parseColor("#3A3B3C")
                priceAmountLayout.boxStrokeColor = Color.parseColor("white")
                availableAmountLayout.boxBackgroundColor = Color.parseColor("#3A3B3C")
                availableAmountLayout.boxStrokeColor = Color.parseColor("white")
                availableAmountInput.setTextColor(Color.parseColor("white"))
                descriptionLayout.boxBackgroundColor = Color.parseColor("#3A3B3C")
                descriptionLayout.boxStrokeColor = Color.parseColor("white")
                productDescription.setTextColor(Color.parseColor("white"))
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
         * @return A new instance of fragment AddProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}