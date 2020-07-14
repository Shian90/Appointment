package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Notification
import kotlinx.android.synthetic.main.cardview_notification.view.*

class NotificationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Notification> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NotificationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview_notification,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is NotificationViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(titleList: List<Notification>){
        items = titleList

    }

    class NotificationViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val notificationTitle: TextView = itemView.txt
        fun bind(notificationText: Notification){

            notificationTitle.setText(notificationText.title)
        }
    }

}