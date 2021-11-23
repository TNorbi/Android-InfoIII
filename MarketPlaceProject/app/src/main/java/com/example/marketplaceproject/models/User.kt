package com.example.marketplaceproject.models


import android.text.Html
import com.squareup.moshi.JsonClass

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="")

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int?,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    var code: String,
    var message: String,
    var creation_time: Long,
    var timestamp: Long?
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    var username: String,
    var password: String,
    var email: String,
    var phone_number: String,
    var firebase_token:String,
    //var userImage : String
)

@JsonClass(generateAdapter = true)
data class ActivateResponse(
    var code : String?,
    var message : String?,
    var timestamp: Long?,
    var htmlResponse : Html
)