package com.example.marketplaceproject.repository

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

    suspend fun activateUser(username: String){
        return RetrofitInstance.api.activateUser(username)
    }
}