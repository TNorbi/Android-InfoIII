package com.example.marketplaceproject.repository

import com.example.marketplaceproject.api.RetrofitInstance
import com.example.marketplaceproject.models.LoginRequest
import com.example.marketplaceproject.models.LoginResponse
import com.example.marketplaceproject.models.ProductResponse
import com.example.marketplaceproject.models.RegisterRequest

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun register(request: RegisterRequest){
        return RetrofitInstance.api.register(request)
    }
}