package com.example.marketplaceproject.viewModels.timeline

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.FilterRequest
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.models.SortRequest
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch

class TimelineViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()

    //var updateTimeLineList : MutableLiveData<Boolean> = MutableLiveData()
    var adapterCurrentPosition = 0

    init {
        Log.d("xxx", "ListViewModel constructor - Token: ${TokenApplication.token}")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(
                        TokenApplication.token,
                        Int.MAX_VALUE
                    )
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            } catch (e: Exception) {
                Log.d("xxx", "ListViewMofdel exception: $e")
            }
        }
    }

    fun getOwnerProducts(username: String) {
        viewModelScope.launch {
            val request = FilterRequest(username = username)

            try {
                Log.d("xxx", "Listviewmodel: request: $request")
                val response = repository.getOwnerProducts(TokenApplication.token, request)
                Log.d("xxx", "Listviewmodel: response: $response")

                products.value = response.products
                Log.d("xxx", "ListViewModel - #products:  ${response.item_count}")
            } catch (e: Exception) {
                Log.d("xxx", "ListViewMofdel exception: $e")
            }
        }
    }
}