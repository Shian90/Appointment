package com.example.app_ointmentt.tools.adaptersNew

import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.RatedDoctor
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rated_doctor_adapter_layout.view.*

class RatedDoctorAdapter (val rateddoc: RatedDoctor) : Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.doctorName_tv.text = rateddoc.doctor.name
        viewHolder.itemView.doctorSpecialty_tv.text = rateddoc.doctor.specialty
        viewHolder.itemView.doctorRating_tv.text = rateddoc.rating.average
    }

    override fun getLayout(): Int {
        return R.layout.rated_doctor_adapter_layout
    }
}