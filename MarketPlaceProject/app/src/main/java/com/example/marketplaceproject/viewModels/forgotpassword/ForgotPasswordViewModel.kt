package com.example.marketplaceproject.viewModels.forgotpassword

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.models.ResetPasswordRequest
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class ForgotPasswordViewModel(val context: Context,val repository: Repository): ViewModel() {
    var user = MutableLiveData<User>()
    var code = MutableLiveData<Int>()

    init {
        user.value = User()
    }

    fun resetUserPassword(){
        viewModelScope.launch {
            val request = ResetPasswordRequest(username = user.value!!.username,email = user.value!!.email)

            try {
                val response = repository.resetUserPassword(request)
                code.value = response.code
            }catch (e: Exception){
                //itt le kell kezeljem majd a hibakat
                Log.d("xxx","ForgotPasswordViewmodel: $e")
            }
        }
    }
}