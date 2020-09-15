package com.example.app_ointmentt.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Appointment
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cardview_history.view.*

class HistoryAdapter(val histories: Appointment, val type: String) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.cardview_history
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if(type == "patient"){
            viewHolder.itemView.txt.text = "You had an appointment with ${histories.slot.doctor.name} on ${histories.slot.dateOfSlot}"
        }
        else if(type == "doctor") {
            viewHolder.itemView.txt.text =
                "You had an appointment with ${histories.patient.name} on ${histories.slot.dateOfSlot}"
        }
    }
}