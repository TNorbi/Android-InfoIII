package com.example.marketplaceproject.viewModels.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.repository.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(val repository: Repository) : ViewModel(){
    var user = MutableLiveData<User>()
    var code = MutableLiveData<Int>()

    init {
        user.value = User()
    }

    fun getUserInfo(){
        viewModelScope.launch {
            try{
                val response = repository.getUserInfo(user.value!!.username) //lekerem a user adatait
                //lementem a response-ban kapott User adatait
                user.value!!.email = response.data[0].email
                user.value!!.phone_number = response.data[0].phone_number.toString()
                code.value = response.code
            }catch (e : Exception){
                Log.d("xxx","ProfileViewModel - exception: $e")
            }
        }
    }
}