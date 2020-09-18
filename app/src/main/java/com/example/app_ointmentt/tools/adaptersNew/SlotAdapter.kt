package com.example.app_ointmentt.tools.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Slot
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.slot_adapter.view.*

class SlotAdapter(val slot: Slot) : Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        var statusText: String
        if ( slot.status == 0 )
            statusText = "Free"
        else
            statusText = "Booked"
        viewHolder.itemView.slotDate.text = slot.dateOfSlot
        viewHolder.itemView.slotStart.text = slot.startTime
        viewHolder.itemView.slotEnd.text = slot.endTime
        viewHolder.itemView.slotStatus.text = statusText
    }

    override fun getLayout(): Int {
        return R.layout.slot_adapter
    }
}