package com.example.app_ointmentt.ui.patient.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.SlotDB
import com.example.app_ointmentt.models.Slot
import com.example.app_ointmentt.tools.adaptersNew.SlotAdapter
import com.example.app_ointmentt.tools.onClickListeners.SlotOnClickListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_request_appointment.view.*


class PatientRequestAppointment : Fragment(),SlotDB.viewAllSlotsByDoctorSuccessListener,SlotDB.viewAllSlotsByDoctorFailureListener {

    private lateinit var doctorName: String
    private lateinit var doctorId:String
    private lateinit var doctorSpecialty: String
    private lateinit var doctorAddress: String
    lateinit var slotDB: SlotDB
    lateinit var mContext: Context
    lateinit var slotsOverall: ArrayList<Slot>
    lateinit var slotsRecyclerView: RecyclerView
    lateinit var dialog: AlertDialog

    companion object {
        private const val ARGS_DOCTOR_NAME = "doctorName"
        private const val ARGS_DOCTOR_ID = "doctorId"
        private const val ARGS_DOCTOR_SPECIALTY = "doctorSpecialty"
        private const val ARGS_DOCTOR_ADDRESS = "doctorAddress"
        fun newInstance(doctorName: String,doctorId: String,doctorSpecialty: String,doctorAddress: String): PatientRequestAppointment {
            val fragment =
                PatientRequestAppointment()
            val args  = Bundle()
            args.putString(ARGS_DOCTOR_NAME,doctorName)
            args.putString(ARGS_DOCTOR_ID,doctorId)
            args.putString(ARGS_DOCTOR_SPECIALTY,doctorSpecialty)
            args.putString(ARGS_DOCTOR_ADDRESS,doctorAddress)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mContext = this.context!!
        slotsOverall = arrayListOf()

        val view = inflater.inflate(R.layout.fragment_request_appointment,container,false)
        if(arguments != null){
            doctorName = arguments!!.getString(ARGS_DOCTOR_NAME).toString()
            doctorId = arguments!!.getString(ARGS_DOCTOR_ID).toString()
            doctorSpecialty = arguments!!.getString(ARGS_DOCTOR_SPECIALTY).toString()
            doctorAddress = arguments!!.getString(ARGS_DOCTOR_ADDRESS).toString()
        }
        view.doctorNameRequestAppointment.text = doctorName
        view.doctorNameRequestAppointment.setOnClickListener {
            dialog = AlertDialog.Builder(context).create()
            val tempDialogView = layoutInflater.inflate(R.layout.profile_sneak_peek, null)
            tempDialogView.findViewById<TextView>(R.id.ProfileUsername).text = doctorName
            tempDialogView.findViewById<TextView>(R.id.ProfileAddress).text = doctorAddress
            tempDialogView.findViewById<TextView>(R.id.ProfileGender).text = "KICCHU EKTA DAOWA LAGBE"
            tempDialogView.findViewById<TextView>(R.id.ProfileBlood).text = "KICCHU EKTA DAOWA LAGBE"
            tempDialogView.findViewById<TextView>(R.id.ProfilePhone).text = "KICCHU EKTA DAOWA LAGBE"
            tempDialogView.findViewById<TextView>(R.id.ProfileSpecial).text = doctorSpecialty
            dialog.setView(tempDialogView)
            dialog.setCancelable(true)
            dialog.show()
        }
        val doctorDetails = "$doctorSpecialty - $doctorAddress"
        view.doctorDetailsRequestAppointment.text = doctorDetails
        slotsRecyclerView = view.findViewById(R.id.doctor_slot_recycler_view)

        slotDB = SlotDB(mContext)

        slotDB.setViewAllSlotsByDoctorFailureListener(this)
        slotDB.setViewAllSlotsByDoctorSuccessListener(this)

        return view
    }

    override fun onStart() {
        slotDB.viewAllUnbookedSlotsByDoctor(doctorId)
        super.onStart()
    }


    override fun viewAllSlotsByDoctorSuccessListener(slotsArray: ArrayList<Slot>) {
        slotsArray.forEach {
            slotsOverall.add(it)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val slotsRecyclerAdapter = GroupAdapter<GroupieViewHolder>()
        slotsOverall.forEach {
            slotsRecyclerAdapter.add(SlotAdapter(it))
        }
        slotsRecyclerView.layoutManager = LinearLayoutManager(mContext)
        val listener = SlotOnClickListener(mContext, "patient")
        slotsRecyclerAdapter.setOnItemClickListener(listener)
        slotsRecyclerView.adapter = slotsRecyclerAdapter
    }

    override fun viewAllSlotsByDoctorFailureListener(message: String?) {
        Log.d("Slot View", "No Slot Found")
    }

}