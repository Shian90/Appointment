package com.example.app_ointmentt.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Appointment
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cardview_notification.view.*

class NotificationAdapter(val notifications: Appointment, val type: String): Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.cardview_notification
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if(type=="patient"){
            viewHolder.itemView.txt.text = "You have an appointment with ${notifications.slot.doctor.name} on ${notifications.slot.dateOfSlot}"
        }
        else if(type == "doctor") {
            viewHolder.itemView.txt.text = "You have an appointment with ${notifications.patient.name} on ${notifications.slot.dateOfSlot}"
        }
    }
}