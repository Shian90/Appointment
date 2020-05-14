package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.DoctorType
import com.example.app_ointmentt.models.History
import kotlinx.android.synthetic.main.cardview_notification.view.*

class DoctorListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<DoctorType> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DoctorTypeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview_patient_homepage,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is DoctorTypeView -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(titleList: List<History>){
        items = titleList

    }

    class DoctorTypeViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val historyTitle: TextView = itemView.txt
        fun bind(historyText: History){
            historyTitle.setText(historyText.title)
        }
    }
}