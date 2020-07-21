package com.example.app_ointmentt.ui.patient.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adapter.DoctorTypeAdapter
import com.example.app_ointmentt.dataset.doctorTypeData
import kotlinx.android.synthetic.main.fragment_patient_homepage.view.*

class PatientHomeFragment : Fragment() {
    private lateinit var doctorTypeAdapter: DoctorTypeAdapter
    private lateinit var iHomepage: IHomepage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("Appointment")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_patient_homepage, container, false)
        initRecyclerView(view)
        bindData()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }
    private fun bindData(){
        doctorTypeAdapter.submitList(doctorTypeData.members)
    }

    private fun initRecyclerView(rootView: View){
        rootView.doctorTyperRecyclerView.apply {
            doctorTypeAdapter =  DoctorTypeAdapter()
            adapter = doctorTypeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}

