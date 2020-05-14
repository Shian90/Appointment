package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.DoctorType
import kotlinx.android.synthetic.main.cardview_notification.view.*

class DoctorTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<DoctorType> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorTypeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_patient_homepage, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is DoctorTypeViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(titleList: List<DoctorType>){
        items = titleList

    }

    class DoctorTypeViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val doctorTypeTitle: TextView = itemView.txt
        fun bind(doctorType: DoctorType){
            doctorTypeTitle.setText(doctorType.title)
        }
    }
}