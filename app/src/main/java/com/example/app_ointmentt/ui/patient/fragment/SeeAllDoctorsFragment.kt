package com.example.app_ointmentt.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adapter.AllDoctorAdapter
import com.example.app_ointmentt.dataset.doctor
import kotlinx.android.synthetic.main.fragment_see_all_doctors.view.*

class SeeAllDoctorsFragment : Fragment() {
    private lateinit var doctorAdapter: AllDoctorAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_see_all_doctors,container,false)
        initRecyclerView(view.allDoctorList)
        bindData()
        return view
    }

    private fun bindData(){
        doctorAdapter.submitList(doctor.members)
    }

    private fun initRecyclerView(rootView: View){
        rootView.allDoctorList.apply {
            doctorAdapter =
                AllDoctorAdapter()
            adapter = doctorAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}