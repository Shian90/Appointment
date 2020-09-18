package com.example.app_ointmentt.ui.patient.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.tools.adaptersNew.HistoryAdapter
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.models.Appointment
import com.example.app_ointmentt.tools.onClickListeners.AppointmentOnClickListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class PatientHistoryFragment : Fragment(),
    AppointmentDB.ViewPastAppointmentsPatientSuccessListener,
    AppointmentDB.ViewPastAppointmentsPatientFailureListener {
    private lateinit var iHomepage: IHomepage
    lateinit var patientHistoryRecylerView: RecyclerView
    lateinit var mContext: Context
    lateinit var appdb: AppointmentDB
    lateinit var patientHistories: ArrayList<Appointment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("History")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_patient_history,container,false)

        patientHistoryRecylerView = view.findViewById(R.id.historyRecyclerView)

        mContext = this.context!!

        appdb = AppointmentDB(mContext)
        appdb.setViewPastAppointmentsPatientSuccessListener(this)
        appdb.setViewPastAppointmentsPatientFailureListener(this)

        patientHistories = arrayListOf()

        return view
    }

    override fun onStart() {
        appdb.viewPastAppointmentsPatient()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }

    private fun initRecyclerView(){
        val patientHistoryRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        patientHistories.forEach{
            patientHistoryRecyclerViewAdapter.add(HistoryAdapter(it, "patient"))
        }
        patientHistoryRecylerView.layoutManager = LinearLayoutManager(mContext)
        val listener = AppointmentOnClickListener(mContext, "patient", "history")
        patientHistoryRecyclerViewAdapter.setOnItemClickListener(listener)
        patientHistoryRecylerView.adapter = patientHistoryRecyclerViewAdapter
    }

    override fun viewPastAppointmentsPatientSuccess(appointments: ArrayList<Appointment>) {
        appointments.forEach{
            patientHistories.add(it)
        }
        initRecyclerView()
    }

    override fun viewPastAppointmentsPatientFailure(message: String?) {
        Toast.makeText(mContext, "Failed to get histories", Toast.LENGTH_SHORT).show()
        Log.d("viewPastAppointmentsPat", "Failed: ${message.toString()}")
    }
}