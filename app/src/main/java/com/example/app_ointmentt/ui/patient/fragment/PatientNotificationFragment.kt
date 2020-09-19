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
import com.example.app_ointmentt.tools.adaptersNew.NotificationAdapter
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.models.Appointment
import com.example.app_ointmentt.tools.onClickListeners.AppointmentOnClickListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class PatientNotificationFragment : Fragment(),
    AppointmentDB.ViewUpcomingAppointmentsPatientSuccessListener,
    AppointmentDB.ViewUpcomingAppointmentsPatientFailureListener {
    private lateinit var iHomepage: IHomepage
    lateinit var patientNotificationRecyclerView: RecyclerView
    lateinit var patientNotifications: ArrayList<Appointment>
    lateinit var appdb: AppointmentDB
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("Notification")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_patient_notification,container,false)

        patientNotificationRecyclerView = view.findViewById(R.id.notificationRecyclerView)

        mContext = this.context!!

        appdb = AppointmentDB(mContext)
        appdb.setViewUpcomingAppointmentsPatientSuccessListener(this)
        appdb.setViewUpcomingAppointmentsPatientFailureListener(this)

        patientNotifications = arrayListOf()

        return view
    }

    override fun onStart() {
        appdb.viewUpcomingAppointmentsPatient()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }

    override fun onResume() {
        patientNotifications = arrayListOf()
        super.onResume()
    }

    private fun initRecyclerView(){
        Log.d("NOTIFDEBUG", "CALLED initRecyclerView")
        val patientNotificationRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        patientNotifications.forEach{
           patientNotificationRecyclerViewAdapter.add(NotificationAdapter(it, "patient"))
        }
        patientNotificationRecyclerView.layoutManager = LinearLayoutManager(mContext)
        val listener = AppointmentOnClickListener(mContext, "patient", "notif")
        patientNotificationRecyclerViewAdapter.setOnItemClickListener(listener)
        patientNotificationRecyclerView.adapter = patientNotificationRecyclerViewAdapter

    }

    override fun viewUpcomingAppointmentsPatientSuccess(appointments: ArrayList<Appointment>) {
        appointments.forEach{
            patientNotifications.add(it)
        }
        initRecyclerView()
    }

    override fun viewUpcomingAppointmentsPatientFailure(message: String?) {
        Toast.makeText(mContext, "Failed to get notifications", Toast.LENGTH_SHORT).show()
        Log.d("viewUpcomingAppPat", "Failed: ${message.toString()}")
    }
}