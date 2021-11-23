package com.example.marketplaceproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplaceproject.R
import com.example.marketplaceproject.adapters.TimelineAdapter
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.repository.Repository
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModel
import com.example.marketplaceproject.viewModels.timeline.TimelineViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeLineFragment : Fragment(),TimelineAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var timelineViewModel: TimelineViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TimelineAdapter
    private lateinit var bottomNavigationMenuView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //letrehozom a timeline viewmodeljet
        val factory = TimelineViewModelFactory(Repository())
        timelineViewModel = ViewModelProvider(this,factory).get(TimelineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timeline, container, false)

        view?.apply {
            //inicializalasok
            setupRecyclerView(view)
            bottomNavigationMenuView = activity!!.findViewById(R.id.bottom_navigation)
        }

        //megjelenitem a menut a timeline fragmentben
        bottomNavigationMenuView.visibility = View.VISIBLE

        timelineViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(timelineViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView(view: View){
        adapter = TimelineAdapter(ArrayList<Product>(), this.requireContext(),this)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun onDetailsClick(position: Int) {
        Log.d("xxx","Itt vagyok")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeLineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}