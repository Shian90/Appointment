package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.History
import com.example.app_ointmentt.models.Notification
import kotlinx.android.synthetic.main.cardview_notification.view.*

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<History> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is HistoryViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(titleList: List<History>){
        items = titleList

    }

    class HistoryViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val historyTitle: TextView = itemView.txt
        fun bind(historyText: History){

            historyTitle.setText(historyText.title)
        }
    }

}