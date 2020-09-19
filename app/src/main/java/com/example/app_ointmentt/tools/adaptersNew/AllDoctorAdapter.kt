package com.example.app_ointmentt.tools.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Doctor
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cardview_doctor_list.view.*

class AllDoctorAdapter (val allDocs: Doctor) : Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.txt!!.text = allDocs.name
    }

    override fun getLayout(): Int {
        return R.layout.cardview_doctor_list
    }
}