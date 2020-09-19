package com.example.app_ointmentt.tools.onClickListeners

import android.content.Context
import android.view.View
import com.example.app_ointmentt.R
import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.ui.patient.fragment.PatientRequestAppointment
import com.example.app_ointmentt.utils.changeFragmentFromFragment
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener

class DoctorOnClickListener(val context: Context) : OnItemClickListener
{
    override fun onItemClick(item: Item<*>, view: View) {
        item as Doctor
        changeFragmentFromFragment(PatientRequestAppointment.newInstance(item.name, item.id, item.specialty, item.address), context, R.id.mainPatient, true)
    }
}