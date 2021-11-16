package com.example.marketplaceproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplaceproject.R
import com.example.marketplaceproject.models.ProductItem

class TimelineAdapter(
    private val list: List<ProductItem>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<TimelineAdapter.DataViewHolder>() {
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val productImageView : ImageView = itemView.findViewById(R.id.productImageView)
        val productPriceView : TextView = itemView.findViewById(R.id.product_price_textview)
        val ownerImageView : ImageView = itemView.findViewById(R.id.owner_picture_imageView)
        val ownerNameView : TextView = itemView.findViewById(R.id.owner_name_textview)
        val productNameView : TextView = itemView.findViewById(R.id.product_name_textview)
        private val orderButtonView : Button = itemView.findViewById(R.id.orderButton)
        init {
            orderButtonView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {

                if(orderButtonView.isPressed){
                    //hogyha a User megnyomja az "Order Now" gombot, akkor a productot meg fogja tudni vasarolni
                }
                else
                {
                    //hogyha a User ranyom maga a CardView-ra(Productra) akkor meg fogja jeleniteni ennek reszleteit
                    listener.onDetailsClick(position)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onDetailsClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position] //itt lekerem a listabol a kurens pozicioban levo elemet

        //a ViewHolder tartalmat fogom frissiteni itt

        //holder.productImageView = currentItem.productImage
        //holder.ownerImageView = currentItem.ownerImage
        holder.ownerNameView.text = currentItem.ownerName
        holder.productNameView.text = currentItem.productName
        holder.productPriceView.text = currentItem.productPrice
    }

    override fun getItemCount() = list.size
}