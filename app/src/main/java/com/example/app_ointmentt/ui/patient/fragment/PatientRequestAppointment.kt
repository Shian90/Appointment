package com.example.app_ointmentt.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.R
import kotlinx.android.synthetic.main.fragment_request_appointment.view.*


class PatientRequestAppointment : Fragment() {
    private lateinit var doctorName: String
    private val ARGS_DOCTOR_NAME = "doctorName"

    companion object {
        private const val ARGS_DOCTOR_NAME = "doctorName"
        fun newInstance(doctorName: String): PatientRequestAppointment {
            val fragment =
                PatientRequestAppointment()
            val args  = Bundle()
            args.putString(ARGS_DOCTOR_NAME,doctorName)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request_appointment,container,false)
        if(arguments != null){
            doctorName = arguments!!.getString(ARGS_DOCTOR_NAME).toString()
        }
        view.doctorNameRequestAppointment.text = doctorName
        view.requestAppointmentbtn.setOnClickListener{
            //Code for requesting Appointment
            val fragment =
                AppointmentConfirmationFragment()
            val activity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.mainPatient,
                fragment,
                fragment.javaClass.simpleName)
                .commit()
        }
        return view
    }

}