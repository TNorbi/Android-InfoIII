package com.example.marketplaceproject.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceproject.R
import com.example.marketplaceproject.repository.Repository
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModel
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailsCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsCustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var timelineViewModel: TimelineViewModel
    private lateinit var ownerImageView: ImageView
    private lateinit var ownerName : TextView
    private lateinit var uploadDate : TextView
    private lateinit var productName : TextView
    private lateinit var pricePerUnit : TextView
    private lateinit var unitAvailable : TextView
    private lateinit var productDescription: TextView
    private lateinit var avabilabilityIcon : ImageView
    private lateinit var availabilityTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //lekerdezem a timeline viewmodeljet
        val factory = TimelineViewModelFactory(Repository())
        timelineViewModel = ViewModelProvider(requireActivity(),factory).get(TimelineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_details_customer, container, false)

        view?.apply {
            initializeView(this)
            displayProductDetails()
        }

        return view
    }

    private fun displayProductDetails() {
        val currentProduct  = timelineViewModel.products.value!![timelineViewModel.adapterCurrentPosition]

        ownerImageView.setImageResource(R.drawable.ic_bazaar_launcher_foreground)
        ownerName.text = currentProduct.username
        uploadDate.text = currentProduct.creation_time.toString()
        productName.text = currentProduct.title
        productDescription.text = currentProduct.description
        pricePerUnit.text = "${currentProduct.price_per_unit} ${currentProduct.price_type}/${currentProduct.amount_type}"
        unitAvailable.text = "Available amount: ${currentProduct.units}${currentProduct.amount_type}"

        if(currentProduct.is_active){
            avabilabilityIcon.setImageResource(R.drawable.ic_active_product)
            availabilityTextView.text = "Active"
            availabilityTextView.setTextColor(Color.parseColor("#33B5E5"))
        }
        else{
            avabilabilityIcon.setImageResource(R.drawable.ic_inactive_product)
            availabilityTextView.text = "Inactive"
            availabilityTextView.setTextColor(Color.parseColor("grey"))
        }

    }

    private fun initializeView(view: View) {
        ownerImageView = view.findViewById(R.id.owner_picture)
        ownerName = view.findViewById(R.id.owner_name)
        uploadDate = view.findViewById(R.id.upload_date)
        productName = view.findViewById(R.id.product_name)
        productDescription = view.findViewById(R.id.product_description)
        pricePerUnit = view.findViewById(R.id.price_per_unit)
        unitAvailable = view.findViewById(R.id.unit_avaible)
        avabilabilityIcon = view.findViewById(R.id.availability_icon)
        availabilityTextView = view.findViewById(R.id.availability_text)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductDetailsCustomerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailsCustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}