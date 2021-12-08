package com.example.marketplaceproject.viewholders

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplaceproject.R
import com.example.marketplaceproject.adapters.MarketAdapter
import com.example.marketplaceproject.models.Product

sealed class MarketRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    class CustomerViewHolder(itemView: View, private val listener: MarketAdapter.OnItemClickListener) : MarketRecyclerViewHolder(itemView),View.OnClickListener{

        private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        private val productPriceView: TextView = itemView.findViewById(R.id.product_price_textview)
        private val ownerImageView: ImageView = itemView.findViewById(R.id.owner_picture_imageView)
        private val ownerNameView: TextView = itemView.findViewById(R.id.owner_name_textview)
        private val productNameView: TextView = itemView.findViewById(R.id.product_name_textview)
        private val orderButtonView: Button = itemView.findViewById(R.id.orderButton)

        init {
            itemView.setOnClickListener(this)
            orderButtonView.setOnClickListener(this)
        }

        fun bind(product: Product){
            val priceText =
                product.price_per_unit + " " + product.price_type + "/" + product.amount_type
            ownerNameView.text = product.username
            productNameView.text = product.title
            productPriceView.text = priceText

            val images = product.images
            if (images != null && images.size > 0) {
                Log.d("xxx", "#num_images: ${images.size}")
            }

            Glide.with(itemView)
                .load(R.drawable.ic_bazaar_launcher_foreground)
                .override(200, 200)
                .into(productImageView)

            Glide.with(itemView)
                .load(R.drawable.ic_bazaar_launcher_foreground)
                .override(200, 200)
                .into(ownerImageView)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {

                if (orderButtonView.isPressed) {
                    //hogyha a User megnyomja az "Order Now" gombot, akkor a productot meg fogja tudni vasarolni
                    return
                }

                //hogyha a User ranyom maga a CardView-ra(Productra) akkor meg fogja jeleniteni ennek reszleteit
                Log.d("xxx", "OnDetailsClick adapterben")
                listener.onDetailsClick(position)
            }
        }
    }

    class OwnerViewHolder(itemView: View, private val listener: MarketAdapter.OnItemClickListener):MarketRecyclerViewHolder(itemView),View.OnClickListener{
        private val productImageView: ImageView = itemView.findViewById(R.id.product_image)
        private val productPriceView: TextView = itemView.findViewById(R.id.price_label)
        private val ownerImageView: ImageView = itemView.findViewById(R.id.owner_image)
        private val ownerNameView: TextView = itemView.findViewById(R.id.owner_name_label)
        private val productNameView: TextView = itemView.findViewById(R.id.product_name_label)
        private val availabilityImageView: ImageView = itemView.findViewById(R.id.avability_imageview)
        private val availabilityLabel : TextView = itemView.findViewById(R.id.avability_label)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: Product){
            val priceText =
                product.price_per_unit + " " + product.price_type + "/" + product.amount_type
            ownerNameView.text = product.username
            productNameView.text = product.title
            productPriceView.text = priceText

            val images = product.images
            if (images != null && images.size > 0) {
                Log.d("xxx", "#num_images: ${images.size}")
            }

            Glide.with(itemView)
                .load(R.drawable.ic_bazaar_launcher_foreground)
                .override(200, 200)
                .into(productImageView)

            Glide.with(itemView)
                .load(R.drawable.ic_bazaar_launcher_foreground)
                .override(200, 200)
                .into(ownerImageView)

            if(product.is_active){
                availabilityImageView.setImageResource(R.drawable.ic_active_product)
                availabilityLabel.text = "Active"
            }
            else{
                availabilityImageView.setImageResource(R.drawable.ic_inactive_product)
                availabilityLabel.text = "Inactive"
            }
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {

                //hogyha a User ranyom maga a CardView-ra(Productra) akkor meg fogja jeleniteni ennek reszleteit
                Log.d("xxx", "OnDetailsClick adapterben")
                listener.onDetailsClick(position)
            }
        }
    }
}