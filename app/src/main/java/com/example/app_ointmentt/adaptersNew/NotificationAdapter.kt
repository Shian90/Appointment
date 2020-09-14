package com.example.app_ointmentt.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Appointment
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cardview_history.view.*

class NotificationAdapter(val notifications: Appointment): Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.fragment_patient_notification
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.txt.text = "You have an appointment with ${notifications.slot.doctor.name} on ${notifications.slot.dateOfSlot}"
    }
}