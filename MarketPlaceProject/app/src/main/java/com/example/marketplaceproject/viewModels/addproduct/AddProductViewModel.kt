package com.example.marketplaceproject.viewModels.addproduct

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class AddProductViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var newProduct = MutableLiveData<Product>()
    var productID = MutableLiveData<String>()
    var modosultProductID: Boolean = false

    init {
        newProduct.value = Product()
    }

    fun addProduct() {
        viewModelScope.launch {
            try {
                val response = repository.addProduct(
                    token = TokenApplication.token,
                    title = newProduct.value!!.title,
                    description = newProduct.value!!.description,
                    price_per_unit = newProduct.value!!.price_per_unit,
                    units = newProduct.value!!.units,
                    is_active = newProduct.value!!.is_active,
                    amount_type = newProduct.value!!.amount_type,
                    price_type = newProduct.value!!.price_type
                )
                modosultProductID = true
                productID.value = response.product_id
                Log.d(
                    "xxx",
                    "AddProductViewModel : new product added with productID: ${productID.value}"
                )

            } catch (e: retrofit2.HttpException) {

                if (e.code() == 300) {
                    Toast.makeText(context, "Token not sent in header.", Toast.LENGTH_SHORT).show()
                }

                if (e.code() == 301) {
                    Toast.makeText(
                        context,
                        "Invalid token.Please try to log in again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (e.code() == 302) {
                    Toast.makeText(
                        context,
                        "Token expired. Token must be refreshed.Please log in again!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (e.code() == 303) {
                    Toast.makeText(
                        context,
                        "Title, description , price or quantity missing.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (e.code() == 304) {
                    Toast.makeText(
                        context,
                        "Error inserting in database.Please try again!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                Log.d("xxx", "AddProductViewModel : exception: $e")
            }
        }
    }
}