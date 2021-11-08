package com.example.quizapplabor7.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplabor7.R
import com.example.quizapplabor7.models.MyItem

class QuizAdapter(
    private val list: List<MyItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<QuizAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val questionTitleView: TextView = itemView.findViewById(R.id.cardView_questionTitle)
        val answerView: TextView = itemView.findViewById(R.id.cardView_goodAnswer)
        private val detailButtonView: Button = itemView.findViewById(R.id.details_button)
        private val deleteButtonView: Button = itemView.findViewById(R.id.delete_button)

        init {
            detailButtonView.setOnClickListener(this)
            deleteButtonView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {

                //hogyha a Details Buttonra kattintott a User, akkor meg kell jeleniteni a kerdes reszleteit
                if (this.detailButtonView.isPressed) {
                    listener.onDetailsClick(position)
                }

                //hogyha a Delete Buttonra kattintott a User, akkor a kivalasztott kerdest ki kell torolni
                if (this.deleteButtonView.isPressed) {
                    listener.onDeleteClick(position)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onDetailsClick(position: Int)
        fun onDeleteClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position] //itt lekerem a listabol a kurens pozicioban levo elemet

        //a ViewHolder tartalmat fogom frissiteni itt
        holder.questionTitleView.text = currentItem.questionTitle
        holder.answerView.text = currentItem.answer
    }

    override fun getItemCount() = list.size

}