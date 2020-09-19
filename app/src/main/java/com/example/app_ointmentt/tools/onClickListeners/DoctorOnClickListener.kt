package com.example.app_ointmentt.tools.onClickListeners

import android.content.Context
import android.view.View
import com.example.app_ointmentt.R
import com.example.app_ointmentt.tools.adaptersNew.AllDoctorAdapter
import com.example.app_ointmentt.ui.patient.fragment.PatientRequestAppointment
import com.example.app_ointmentt.utils.changeFragmentFromFragment
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener

class DoctorOnClickListener(val context: Context) : OnItemClickListener
{
    override fun onItemClick(item: Item<*>, view: View) {
        item as AllDoctorAdapter
        val temp = item.allDocs
        changeFragmentFromFragment(PatientRequestAppointment.newInstance(temp.name, temp.id, temp.specialty, temp.address), context, R.id.mainPatient, true)
    }
}