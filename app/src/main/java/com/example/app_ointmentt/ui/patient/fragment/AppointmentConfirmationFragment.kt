package com.example.app_ointmentt.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.R
import kotlinx.android.synthetic.main.fragment_appointment_confirmation.view.*

class AppointmentConfirmationFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_appointment_confirmation,container,false)
        view.goBackHomeBtn.setOnClickListener{
            val fragment =
                PatientHomeFragment()
            val activity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainPatient,
                fragment,
                fragment.javaClass.simpleName)
                .commit()
        }
        return view
    }
}