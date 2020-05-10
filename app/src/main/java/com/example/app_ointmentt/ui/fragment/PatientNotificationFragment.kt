package com.example.app_ointmentt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adapter.NotificationAdapter
import com.example.app_ointmentt.dataset.Rawdata
import kotlinx.android.synthetic.main.fragment_doctor_notification.view.*

class PatientNotificationFragment : Fragment() {
    private lateinit var notificationAdapter: NotificationAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_doctor_notification,container,false)
        initRecyclerView(rootView)
        bindData()
        return rootView
    }

    private fun bindData(){
        notificationAdapter.submitList(Rawdata.members)
    }

    private fun initRecyclerView(rootView: View){
        rootView.notificationRecyclerView.apply {
            notificationAdapter =
                NotificationAdapter()
            adapter = notificationAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}