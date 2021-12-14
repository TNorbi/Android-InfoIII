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

    suspend fun activateUser(username: String): ActivateResponse {
        return RetrofitInstance.api.activateUser(username)
    }

    suspend fun getUserInfo(username: String): UserInfoResponse {
        return RetrofitInstance.api.getUserInfo(username)
    }

    suspend fun updateUserInfo(token: String, request: UserUpdateRequest): UserUpdateResponse {
        return RetrofitInstance.api.updateUserInfo(token, request)
    }

    suspend fun resetUserPassword(request: ResetPasswordRequest): ResetPasswordResponse {
        return RetrofitInstance.api.resetUserPassword(request)
    }

    suspend fun getOwnerProducts(token: String, filterRequest: FilterRequest): ProductResponse {
        return RetrofitInstance.api.getOwnerProducts(token, filterRequest)
    }

    suspend fun addProduct(
        token: String,
        title: String,
        description: String,
        price_per_unit: String,
        units: String,
        is_active: Boolean,
        amount_type: String,
        price_type: String
    ): AddProductResponse {
        return RetrofitInstance.api.addProduct(
            token,
            title,
            description,
            price_per_unit,
            units,
            is_active,
            amount_type,
            price_type
        )
    }
}