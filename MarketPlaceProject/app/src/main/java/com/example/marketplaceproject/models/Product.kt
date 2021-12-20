package com.example.marketplaceproject.models

import com.squareup.moshi.JsonClass

data class Product(
    var rating: Double = 0.0,
    var amount_type: String = "",
    var price_type: String = "",
    var product_id: String = "",
    var username: String = "",
    var is_active: Boolean = false,
    var price_per_unit: String = "",
    var units: String = "",
    var description: String = "",
    var title: String = "",
    var images: List<Image> = listOf(),
    var creation_time: Long = 0
)

@JsonClass(generateAdapter = true)
data class Image(
    val _id: String,
    val image_id: String,
    val image_name: String,
    val image_path: String
)

@JsonClass(generateAdapter = true)
data class ProductResponse(val item_count: Int, val products: List<Product>, val timestamp: Long)

@JsonClass(generateAdapter = true)
data class FilterRequest(
    var username: String
)

@JsonClass(generateAdapter = true)
data class AddProductResponse(
    var creation: String,
    var product_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var amount_type: String,
    var price_type: String,
    var images: List<Image>,
    var creation_time: Long
)

@JsonClass(generateAdapter = true)
data class SortRequest(
    var creation_time: Int
)