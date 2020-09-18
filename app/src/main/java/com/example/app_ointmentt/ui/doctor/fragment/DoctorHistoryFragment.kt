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
import com.example.app_ointmentt.tools.adaptersNew.HistoryAdapter
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.models.Appointment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class DoctorHistoryFragment : Fragment(), AppointmentDB.ViewPastAppointmentsDoctorSuccessListener,
    AppointmentDB.ViewPastAppointmentsDoctorFailureListener {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var iHomeFragment: IHomepage
    lateinit var doctorHistoryRecylerView: RecyclerView
    lateinit var mContext: Context
    lateinit var appdb: AppointmentDB
    lateinit var doctorHistories: ArrayList<Appointment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("History")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_doctor_history,container,false)

        doctorHistoryRecylerView = view.findViewById(R.id.historyRecyclerView)

        mContext = this.context!!

        appdb = AppointmentDB(mContext)
        appdb.setViewPastAppointmentsDoctorSuccessListener(this)
        appdb.setViewPastAppointmentsDoctorFailureListener(this)

        doctorHistories = arrayListOf()

        return view
    }

    override fun onStart() {
        appdb.viewPastAppointmentsDoctor()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }

    private fun initRecyclerView(){
        val doctorHistoryRecyclerViewAdapter = GroupAdapter<GroupieViewHolder>()
        doctorHistories.forEach{
            doctorHistoryRecyclerViewAdapter.add(HistoryAdapter(it, "doctor"))
        }
        doctorHistoryRecylerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL ,false)
        doctorHistoryRecylerView.adapter = doctorHistoryRecyclerViewAdapter
    }

    override fun viewPastAppointmentsDoctorSuccess(appointments: ArrayList<Appointment>) {
        appointments.forEach{
            doctorHistories.add(it)
        }
        initRecyclerView()
    }

    override fun viewPastAppointmentsDoctorFailure(message: String?) {
        Toast.makeText(mContext, "Failed to get histories", Toast.LENGTH_SHORT).show()
        Log.d("viewPastAppointmentsDoc", "Failed: ${message.toString()}")
    }
}