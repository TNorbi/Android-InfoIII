package com.example.marketplaceproject.viewModels.register

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.LoginRequest
import com.example.marketplaceproject.models.RegisterRequest
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch

class RegisterViewModel(val context: Context,val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun register(){
        viewModelScope.launch {
            val request =
                RegisterRequest(username = user.value!!.username, password = user.value!!.password, email = user.value!!.email, phone_number = user.value!!.phone_number, firebase_token = "token")
            try {
                val result = repository.register(request)
                Log.d("xxx", "Register result:  $result")
            } catch (e: Exception) {
                Log.d("xxx", "RegisterViewModel - exception: $e")
            }
        }
    }
}