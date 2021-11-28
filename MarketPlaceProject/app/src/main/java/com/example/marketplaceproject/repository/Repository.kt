package com.example.marketplaceproject.repository

import android.text.Html
import android.util.Log
import com.example.marketplaceproject.api.RetrofitInstance
import com.example.marketplaceproject.models.*

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun activateUser(username: String) : ActivateResponse {
        return RetrofitInstance.api.activateUser(username)
    }

    suspend fun getUserInfo(username: String) : UserInfoResponse{
        Log.d("xxx","Repository.getUserInfo -> Itt vagyok, username: $username")
        return RetrofitInstance.api.getUserInfo(username)
    }
}