package com.example.marketplaceproject.viewModels.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.TokenApplication
import com.example.marketplaceproject.models.LoginRequest
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    //itt a ViewModel fogja elinditani a coroutine-t es nem a fragment!!!
    fun login() {
        viewModelScope.launch {
            val request =
                LoginRequest(username = user.value!!.username, password = user.value!!.password)
            try {
                val result = repository.login(request)
                TokenApplication.token = result.token
                token.value = result.token
                Log.d("xxx", "MyApplication - token:  ${TokenApplication.token}")
            } catch (e: Exception) {
                Log.d("xxx", "MainViewModel - exception: $e")
            }
        }
    }
}
