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
import com.example.app_ointmentt.adapter.HistoryAdapter
import com.example.app_ointmentt.dataset.PatientHistoryRawData
import kotlinx.android.synthetic.main.fragment_doctor_history.view.*

class PatientHistoryFragment : Fragment() {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var iHomepage: IHomepage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomepage.setToolbarTitle("History")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_patient_history,container,false)
        initRecyclerView(rootView)
        bindData()
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomepage = activity as IHomepage
    }

    private fun bindData(){
        historyAdapter.submitList(PatientHistoryRawData.members)
    }

    private fun initRecyclerView(rootView: View){
        rootView.historyRecyclerView.apply {
            historyAdapter = HistoryAdapter()
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}