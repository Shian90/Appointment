package com.example.app_ointmentt.ui.patient.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.SlotDB
import com.example.app_ointmentt.models.Slot
import kotlinx.android.synthetic.main.fragment_request_appointment.view.*


class PatientRequestAppointment : Fragment(),SlotDB.viewAllSlotsByDoctorSuccessListener,SlotDB.viewAllSlotsByDoctorFailureListener {
    private lateinit var doctorName: String
    private lateinit var doctorId:String

    companion object {
        private const val ARGS_DOCTOR_NAME = "doctorName"
        private const val ARGS_DOCTOR_ID = "doctorId"
        fun newInstance(doctorName: String,doctorId: String): PatientRequestAppointment {
            val fragment =
                PatientRequestAppointment()
            val args  = Bundle()
            args.putString(ARGS_DOCTOR_NAME,doctorName)
            args.putString(ARGS_DOCTOR_ID,doctorId)
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
            doctorId = arguments!!.getString(ARGS_DOCTOR_ID).toString()
        }
        view.doctorNameRequestAppointment.text = doctorName
        val slotDB = SlotDB(activity as AppCompatActivity)
        slotDB.setViewAllSlotsByDoctorFailureListener(this)
        slotDB.setViewAllSlotsByDoctorSuccessListener(this)
//        view.requestAppointmentbtn.setOnClickListener{
//            //Code for requesting Appointment
//            slotDB.viewAllSlotsByDoctor(doctorId)
//        }
        return view
    }

    override fun viewAllSlotsByDoctorSuccessListener(slotsArray: ArrayList<Slot>) {
        Log.d("Slot View", slotsArray[0].startTime + " " + slotsArray[0].endTime)
        val fragment =
            AppointmentConfirmationFragment()
        val activity = view!!.context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction().replace(R.id.mainPatient,
            fragment,
            fragment.javaClass.simpleName)
            .commit()
    }

    override fun viewAllSlotsByDoctorFailureListener(message: String?) {
        Log.d("Slot View", "No Slot Found")
    }

}