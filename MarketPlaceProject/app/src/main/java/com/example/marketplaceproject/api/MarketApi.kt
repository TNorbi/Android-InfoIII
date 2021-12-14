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
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET(Constants.ACTIVATE_USER_URL)
    suspend fun activateUser(@Query("username") username: String): ActivateResponse

    @GET(Constants.GET_USER_INFO_URL)
    suspend fun getUserInfo(@Header("username") username: String): UserInfoResponse

    @POST(Constants.UPDATE_USER_DATA_URL)
    suspend fun updateUserInfo(
        @Header("token") token: String,
        @Body request: UserUpdateRequest
    ): UserUpdateResponse

    @POST(Constants.FORGOT_PASSWORD_POST_URL)
    suspend fun resetUserPassword(@Body request: ResetPasswordRequest): ResetPasswordResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getOwnerProducts(
        @Header("token") token: String,
        @Header("filter") filterRequest: FilterRequest
    ): ProductResponse

    @Multipart
    @POST(Constants.ADD_PRODUCT_URL)
    suspend fun addProduct(
        @Header("token") token: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("price_per_unit") price_per_unit: String,
        @Part("units") units: String,
        @Part("is_active") isActive: Boolean,
        @Part("amount_type") amountType: String,
        @Part("price_type") priceType: String
    ) : AddProductResponse
}