package com.example.marketplaceproject.api

import com.example.marketplaceproject.models.LoginRequest
import com.example.marketplaceproject.models.LoginResponse
import com.example.marketplaceproject.models.ProductResponse
import com.example.marketplaceproject.utils.Constants
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}