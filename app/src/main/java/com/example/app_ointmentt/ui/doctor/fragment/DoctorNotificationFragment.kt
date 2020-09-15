package com.example.app_ointmentt.ui.doctor.fragment

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
import com.example.app_ointmentt.adaptersNew.NotificationAdapter
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.dataset.Rawdata
import com.example.app_ointmentt.models.Appointment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_doctor_notification.view.*

class DoctorNotificationFragment : Fragment(),
    AppointmentDB.ViewUpcomingAppointmentsDoctorSuccessListener,
    AppointmentDB.ViewPastAppointmentsDoctorFailureListener {
    private lateinit var iHomeFragment: IHomepage
    lateinit var doctorNotificationRecyclerView: RecyclerView
    lateinit var doctorNotifications: ArrayList<Appointment>
    lateinit var appdb: AppointmentDB
    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("Notification")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_doctor_notification,container,false)

        doctorNotificationRecyclerView = view.findViewById(R.id.notificationRecyclerView)

        mContext = this.context!!

        appdb = AppointmentDB(mContext)
        appdb.setViewUpcomingAppointmentsDoctorSuccessListener(this)
        appdb.setViewPastAppointmentsDoctorFailureListener(this)

        doctorNotifications = arrayListOf()

        return view
    }

    override fun onStart() {
        appdb.viewUpcomingAppointmentsDoctor()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }



    private fun initRecyclerView(){
        val doctorNotificationRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        doctorNotifications.forEach{
            doctorNotificationRecyclerViewAdapter.add(NotificationAdapter(it))
        }
        doctorNotificationRecyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL ,false)
        doctorNotificationRecyclerView.adapter = doctorNotificationRecyclerViewAdapter

    }

    override fun viewUpcomingAppointmentsDoctorSuccess(appointments: ArrayList<Appointment>) {
        appointments.forEach{
            doctorNotifications.add(it)
        }
        initRecyclerView()
    }

    override fun viewPastAppointmentsDoctorFailure(message: String?) {
        Toast.makeText(mContext, "Failed to get notifications", Toast.LENGTH_SHORT).show()
        Log.d("viewUpcomingAppDoc", "Failed: ${message.toString()}")
    }
}