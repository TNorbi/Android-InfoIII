package com.example.marketplaceproject.viewModels.timeline

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.FilterRequest
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.models.ProductResponse
import com.example.marketplaceproject.models.SortRequest
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch

class TimelineViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var editOwnerProduct = false
    var adapterCurrentPosition = 0
    var deletedProductID = MutableLiveData<String>()
    var modosultDeletedProductID = false

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

                val list = listWithoutDoubleQuotes(result)
                Log.d("xxx", "List: $list")
                products.value = list
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            } catch (e: Exception) {
                Log.d("xxx", "ListViewMofdel exception: $e")
            }
        }
    }

    private fun listWithoutDoubleQuotes(result: ProductResponse): MutableList<Product> {
        val resultList = mutableListOf<Product>()

        for (product in result.products) {
            if (product.title.contains(Regex("^\"|\"$"))) {
                product.title = product.title.replace("\"", "")
            }

            if (product.description.contains(Regex("^\"|\"$"))) {
                product.description = product.description.replace("\"", "")
            }

            if (product.units.contains(Regex("^\"|\"$"))) {
                product.units = product.units.replace("\"", "")
            }

            if (product.amount_type.contains(Regex("^\"|\"$"))) {
                product.amount_type = product.amount_type.replace("\"", "")
            }

            if (product.price_type.contains(Regex("^\"|\"$"))) {
                product.price_type = product.price_type.replace("\"", "")
            }

            if (product.price_per_unit.contains(Regex("^\"|\"$"))) {
                product.price_per_unit = product.price_per_unit.replace("\"", "")
            }

            resultList.add(product)
        }

        return resultList
    }

    fun deleteProduct() {
        viewModelScope.launch {
            try {
                val response = repository.deleteProduct(
                    token = TokenApplication.token,
                    product_id = products.value!![adapterCurrentPosition].product_id
                )

                modosultDeletedProductID = true
                deletedProductID.value = response.product_id
            } catch (e: Exception) {
                Log.d("xxx", "TimeLineViewModel deleteProduct: $e")
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