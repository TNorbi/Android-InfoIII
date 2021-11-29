package com.example.marketplaceproject.viewModels.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.models.UserUpdateRequest
import com.example.marketplaceproject.models.UserUpdateResponse
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()
    var code = MutableLiveData<Int>()
    var token = MutableLiveData<String>()

    init {
        user.value = User()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val response =
                    repository.getUserInfo(user.value!!.username) //lekerem a user adatait
                //lementem a response-ban kapott User adatait
                user.value!!.email = response.data[0].email
                user.value!!.phone_number = response.data[0].phone_number.toString()
                code.value = response.code
            } catch (e: Exception) {
                Log.d("xxx", "ProfileViewModel - exception: $e")
            }
        }
    }

    fun updateUserInfo() {
        viewModelScope.launch {
            val request = UserUpdateRequest(
                username = user.value!!.username,
                email = user.value!!.email,
                phone_number = user.value!!.phone_number
            )

            try {
                val response = repository.updateUserInfo(TokenApplication.token, request)
                user.value!!.username = response.updatedData[0].username
                user.value!!.email = response.updatedData[0].email
                user.value!!.phone_number = response.updatedData[0].phone_number.toString()
                TokenApplication.username = response.updatedData[0].username
                TokenApplication.token = response.updatedData[0].token
                token.value = response.updatedData[0].token
            } catch (e: Exception) {
                Log.d("xxx", "ProfileViewModel - exception: $e")
            }

        }
    }
}