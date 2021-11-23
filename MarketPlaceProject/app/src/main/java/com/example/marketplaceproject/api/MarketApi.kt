package com.example.marketplaceproject.api

import com.example.marketplaceproject.models.*
import com.example.marketplaceproject.utils.Constants
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest) : RegisterResponse

    @GET(Constants.ACTIVATE_USER_URL)
    suspend fun activateUser(@Query("username") username: String ) : ActivateResponse
}