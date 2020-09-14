package com.example.app_ointmentt.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Doctor
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_see_all_doctors.view.*

class AllDoctorAdapter (val alldocs: Doctor) : Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.txt.text = alldocs.name
    }

    override fun getLayout(): Int {
        return R.layout.fragment_see_all_doctors
    }
}